package com.opsDashboard.vehicle;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class VehicleServiceUnitTest
{
//    VehicleDTO getVehicleByStockIdAsDto(final String stockId)
//    {
//        return toDto(getVehicleByStockId(stockId));
//    }
//
//    private VehicleDTO toDto(final Vehicle vehicle)
//    {
//        return new VehicleDTO(
//            vehicle.getStockId()
//            , vehicle.getManufacturer()
//            , vehicle.getModel()
//        );
//    }

    @Test
    void getVehicleByStockId_shouldThrowExceptionWhenStockIdNotFound()
    {
//        given
        var stockId = "XX12345";

        var mockVehicleRepo = mock(VehicleRepository.class);
        given(mockVehicleRepo.findByStockId(anyString()))
                .willReturn(Optional.empty());

//        system under test
        var toTest = new VehicleService(mockVehicleRepo, null);

//        when
        var result = catchThrowable(() -> toTest.getVehicleByStockId(stockId));

//        then
        assertThat(result)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("given stock id not found");
    }

    @Test
    void getVehicleByStockId_shouldReturnVehicleWhenStockIdFound()
    {
//        given
        var stockId = "XX12345";

        var mockVehicleRepo = mock(VehicleRepository.class);
        given(mockVehicleRepo.findByStockId(anyString()))
                .willReturn(Optional.of(new Vehicle()));

//        system under test
        var toTest = new VehicleService(mockVehicleRepo, null);

//        when
        var result = toTest.getVehicleByStockId(stockId);

//        then
        assertNotNull(result);
        assertInstanceOf(Vehicle.class, result);
    }

//    void requestPUCode(final PUCodeRequestDTO request)
//    {
//        var lc = getLCById(getVehicleByStockId(request.getStockId()).getLocation().getId());
//        var puDate = request.getPuDate();
//
//        if (puDate.equals(LocalDate.now().plusDays(1)))
//            if (request.getTime().isAfter(lc.getNextDayRequestLatestTime()))
//                puDate = getNextAvailableDayByDateAndLC(puDate, lc);
//
//
//        if (lc.checkIfLCIsClosedByDate(puDate))
//            puDate = getNextAvailableDayByDateAndLC(puDate, lc);
//
//        // check if there are still available slots if not propose next available day
//
//        checkIfAreAvailableSlotsByDateAndLC(puDate, lc);
//
//        getNextAvailableDayByDateAndLC(puDate, lc);
//
////        check if Lc is working on given date
////                check if has available pu slots
////                if not propose next available day
//
////                notify LC about pu request or propose merchant next available date?
//    }


    @Test
    void getNextAvailableDayByDateAndLC_shouldReturnPUDatePlusOneIfVeryNextDayIsAvailable()
    {
//        given
        var puDate = LocalDate.now();
        var puSlots = 100;

        var weekday = puDate.plusDays(1).getDayOfWeek();
        var workdays = Set.of(new LogisticCenter.Workday(weekday, LocalTime.now(), LocalTime.now(), puSlots));
        var lc = new LogisticCenter("foo", "bar", workdays, new HashSet<>(), LocalTime.now());

        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findPuRequestCountByDateAndLCIdAndStatus(any(), anyInt(), any()))
                .willReturn(Optional.of(puSlots - 5));

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = toTest.getNextAvailableDayByDateAndLC(puDate, lc);

//        then
        assertEquals(puDate.plusDays(1), result);
    }
//    LocalDate getNextAvailableDayByDateAndLC(final LocalDate puDate, final LogisticCenter lc)
//    {
//        var toCheck = puDate.plusDays(1);
//        var workingWeekdays = lc.getWorkdaysDaysOfWeek();
//
//        toCheck = getNextWorkingDay(toCheck, workingWeekdays);    TESTED
//
//        if (lc.checkIfLCIsClosedByDate(puDate))
//            toCheck = getNextWorkingDay(toCheck, workingWeekdays);
//
//        if(!checkIfAreAvailableSlotsByDateAndLC(toCheck, lc))
//        {
//            toCheck = getNextWorkingDay(toCheck, workingWeekdays);
//
//            if (lc.getRestrictedDaysDates().contains(puDate))
//                if (lc.getRestrictionTypeByDate(puDate).equals(LogisticCenter.RestrictedDay.Type.CLOSED))
//                    toCheck = getNextWorkingDay(toCheck, workingWeekdays);
//        }
//
//        return toCheck;
//    }

    @Test
    void getNextWorkingDay_shouldReturnNextWorkingDayAfterGivenDateFromLCWorkingWeekdaysSet()
    {
//        given
        var toCheck1 = LocalDate.of(2024, 4, 4); // -> THURSDAY
        var toCheck2 = LocalDate.of(2024, 4, 9); // -> TUESDAY
        var workingWeekdays = Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);

//        system under test
        var toTest = new VehicleService(null, null);

//        when
        var result1 = toTest.getNextWorkingDay(toCheck1, workingWeekdays);
        var result2 = toTest.getNextWorkingDay(toCheck2, workingWeekdays);

//        then
        assertEquals(LocalDate.of(2024, 4, 8), result1);
        assertEquals(DayOfWeek.MONDAY, result1.getDayOfWeek());

        assertEquals(LocalDate.of(2024, 4, 10), result2);
        assertEquals(DayOfWeek.WEDNESDAY, result2.getDayOfWeek());
    }

    @Test
    void checkIfAreAvailableSlotsByDateAndLC_shouldReturnFalseIfAcceptedPURequestCountIsNotLesserThanPUSlotsAvailable()
    {
//        given
        var count = 120;
        var puSlots = 100;
        var puDate = LocalDate.now();

        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findPuRequestCountByDateAndLCIdAndStatus(any(), anyInt(), any()))
                .willReturn(Optional.of(count));

        var workdays = Set.of(new LogisticCenter.Workday(puDate.getDayOfWeek(), LocalTime.now(), LocalTime.now(), puSlots));
        var lc = new LogisticCenter("foo", "bar", workdays, new HashSet<>(), LocalTime.now());

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = toTest.checkIfAreAvailableSlotsByDateAndLC(puDate, lc);

//        then
        assertFalse(result);
    }

    @Test
    void checkIfAreAvailableSlotsByDateAndLC_shouldReturnTrueIfAcceptedPURequestCountIsLesserThanPUSlotsAvailable()
    {
//        given
        var count = 90;
        var puSlots = 100;
        var puDate = LocalDate.now();

        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findPuRequestCountByDateAndLCIdAndStatus(any(), anyInt(), any()))
                .willReturn(Optional.of(count));

        var workdays = Set.of(new LogisticCenter.Workday(puDate.getDayOfWeek(), LocalTime.now(), LocalTime.now(), puSlots));
        var lc = new LogisticCenter("foo", "bar", workdays, new HashSet<>(), LocalTime.now());

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = toTest.checkIfAreAvailableSlotsByDateAndLC(puDate, lc);

//        then
        assertTrue(result);
    }

    @Test
    void getPuRequestCountByDateAndLCId_shouldReturnZeroIfAcceptedPUCodeRequestWithGivenDateNotFound()
    {
//        given
        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findPuRequestCountByDateAndLCIdAndStatus(any(), anyInt(), any()))
                .willReturn(Optional.empty());

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = toTest.getPuRequestCountByDateAndLCId(LocalDate.now(), 1);

//        then
        assertEquals(0, result);
    }

    @Test
    void getPuRequestCountByDateAndLCId_shouldReturnAmountOfAcceptedPUCodeRequestWithGivenDateFound()
    {
//        given
        var count = 120;

        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findPuRequestCountByDateAndLCIdAndStatus(any(), anyInt(), any()))
                .willReturn(Optional.of(count));

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = toTest.getPuRequestCountByDateAndLCId(LocalDate.now(), 3);

//        then
        assertEquals(count, result);
    }

    @Test
    void getLCById_shouldThrowExceptionWhenLCNotFound()
    {
//        given
        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findById(anyInt()))
                .willReturn(Optional.empty());

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = catchThrowable(() -> toTest.getLCById(5));

//        then
        assertThat(result)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("LC with given id not found!");
    }

    @Test
    void getLCById_shouldReturnLCWhenFound()
    {
//        given
        var mockLCRepo = mock(LogisticCenterRepository.class);
        given(mockLCRepo.findById(anyInt()))
                .willReturn(Optional.of(new LogisticCenter()));

//        system under test
        var toTest = new VehicleService(null, mockLCRepo);

//        when
        var result = toTest.getLCById(3);

//        then
        assertNotNull(result);
        assertInstanceOf(LogisticCenter.class, result);
    }
}