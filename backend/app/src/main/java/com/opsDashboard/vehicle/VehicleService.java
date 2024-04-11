package com.opsDashboard.vehicle;

import com.opsDashboard.vehicle.dto.PUCodeRequestDTO;
import com.opsDashboard.vehicle.dto.VehicleDTO;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
class VehicleService
{
    private final VehicleRepository vehicleRepo;
    private final LogisticCenterRepository lcRepo;

    VehicleService(VehicleRepository vehicleRepo, LogisticCenterRepository lcRepo)
    {
        this.vehicleRepo = vehicleRepo;
        this.lcRepo = lcRepo;
    }

    VehicleDTO getVehicleByStockIdAsDto(final String stockId)
    {
        return toDto(getVehicleByStockId(stockId));
    }

    private VehicleDTO toDto(final Vehicle vehicle)
    {
        return new VehicleDTO(
            vehicle.getStockId()
            , vehicle.getManufacturer()
            , vehicle.getModel()
        );
    }

    private Vehicle getVehicleByStockId(final String stockId)
    {
//        TODO check czy stock id jest poprawny?

        return this.vehicleRepo.findByStockId(stockId)
                .orElseThrow(() -> new NoSuchElementException("Vehicle with given stock id not found!"));
    }

    void requestPUCode(final PUCodeRequestDTO request)
    {
        var lc = getLCById(getVehicleByStockId(request.getStockId()).getLocation().getId());
        var puDate = request.getPuDate();

        if (puDate.equals(LocalDate.now().plusDays(1)))
            if (request.getTime().isAfter(LocalTime.of(14, 0)))
            {
                getNextAvailableDayByDateAndLC(puDate, lc);
            }

        if (lc.checkIfLCIsClosedByDate(puDate))
                getNextAvailableDayByDateAndLC(request.getPuDate(), lc);

        // check if there are still available slots if not propose next available day

        checkIfAreAvailableSlotsByDateAndLC(puDate, lc);

        getNextAvailableDayByDateAndLC(puDate, lc);

//        check if Lc is working on given date
//                check if has available pu slots
//                if not propose next available day

//                notify LC about pu request



    }

    private LocalDate getNextAvailableDayByDateAndLC(final LocalDate puDate, final LogisticCenter lc)
    {
        var toCheck = puDate.plusDays(1);
        var workingWeekdays = lc.getWorkdaysDaysOfWeek();

        toCheck = getNextWorkingDay(toCheck, workingWeekdays);

        if (lc.checkIfLCIsClosedByDate(puDate))
            toCheck = getNextWorkingDay(toCheck, workingWeekdays);

        if(!checkIfAreAvailableSlotsByDateAndLC(toCheck, lc))
        {
            toCheck = getNextWorkingDay(toCheck, workingWeekdays);

            if (lc.getRestrictedDaysDates().contains(puDate))
                if (lc.getRestrictionTypeByDate(puDate).equals(LogisticCenter.RestrictedDay.Type.CLOSED))
                    toCheck = getNextWorkingDay(toCheck, workingWeekdays);
        }

        return toCheck;
    }

    LocalDate getNextWorkingDay(LocalDate toCheck, final Set<DayOfWeek> workingWeekdays)
    {
        while(0 == 0)
        {
            if (workingWeekdays.contains(toCheck.getDayOfWeek()))
                break;

            toCheck = toCheck.plusDays(1);
        }

        return toCheck;
    }

    private boolean checkIfAreAvailableSlotsByDateAndLC(final LocalDate puDate, final LogisticCenter lc)
    {
//        getPuRequestCountByDateAndLCId(puDate, lc.getId());
        var count = this.lcRepo.findPuRequestCountByDateAndLCIdAndStatus(puDate, lc.getId(), PUCodeRequest.Status.ACCEPTED).orElse(0);

        return count < lc.getSlotsByDate(puDate);
    }

    private LogisticCenter getLCById(final int lcId)
    {
        return this.lcRepo.findById(lcId)
                .orElseThrow(() -> new NoSuchElementException("LC with given id not found!"));
    }

}
