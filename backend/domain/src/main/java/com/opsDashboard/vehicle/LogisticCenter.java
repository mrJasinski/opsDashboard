package com.opsDashboard.vehicle;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

class LogisticCenter
{
    private int id;
    private String name;
    private String address;
    private Set<Workday> workdays;  // working days in week eg MONDAY, TUESDAY etc
//    days when LC is extra closed or has shortened working hours etc
    private Set<RestrictedDay> restrictedDays;
    private LocalTime nextDayRequestLatestTime; //TODO lepsza nazwa

    LogisticCenter()
    {
    }

    LogisticCenter(
            final String name
            , final String address
            , final Set<Workday> workdays
            , final Set<RestrictedDay> restrictedDays
            , final LocalTime nextDayRequestLatestTime)
    {
        this.name = name;
        this.address = address;
        this.workdays = workdays;
        this.restrictedDays = restrictedDays;
        this.nextDayRequestLatestTime = nextDayRequestLatestTime;
    }

    int getId()
    {
        return this.id;
    }

    LocalTime getNextDayRequestLatestTime()
    {
        return this.nextDayRequestLatestTime;
    }

    void addRestrictedDay(RestrictedDay day)
    {
        this.restrictedDays.add(day);
    }

    Set<LocalDate> getRestrictedDaysDates()
    {
        return this.restrictedDays
                .stream()
                .map(RestrictedDay::getRestrictedDate)
                .collect(Collectors.toSet());
    }

    RestrictedDay.Type getRestrictionTypeByDate(final LocalDate date)
    {
        return this.restrictedDays
                .stream()
                .filter(d -> d.getRestrictedDate().equals(date))
                .toList()
                .get(0)
                .getType();
    }

    RestrictedDay getRestrictedDayByDate(final LocalDate puDate)
    {
        return this.restrictedDays
                .stream()
                .filter(d -> d.getRestrictedDate().equals(puDate))
                .toList()
                .get(0);
    }

    int getSlotsByDate(final LocalDate puDate)
    {
        if (getRestrictedDaysDates().contains(puDate))
            return getRestrictedDayByDate(puDate).getWorkday().getPuSlots();

        return this.workdays
                .stream()
                .filter(w -> w.getWeekday().equals(puDate.getDayOfWeek()))
                .toList()
                .get(0)
                .getPuSlots();
    }

    boolean checkIfLCIsClosedByDate(final LocalDate puDate)
    {
        if (getRestrictedDaysDates().contains(puDate))
            return getRestrictionTypeByDate(puDate).equals(RestrictedDay.Type.CLOSED);

        return false;
    }

    void addWorkday(Workday workday)
    {
        this.workdays.add(workday);
    }

    Set<DayOfWeek> getWorkdaysDaysOfWeek()
    {
        return this.workdays
                .stream()
                .map(Workday::getWeekday)
                .collect(Collectors.toSet());
    }

    static class RestrictedDay
    {
        private int id;
        private LocalDate restrictedDate;
        private Workday workday;
        private Type type;

        int getId()
        {
            return this.id;
        }

        LocalDate getRestrictedDate()
        {
            return this.restrictedDate;
        }

        Workday getWorkday()
        {
            return this.workday;
        }

        Type getType()
        {
            return this.type;
        }

        enum Type
        {
            CLOSED
            , SHORTENED_HOURS
            , SHORTENED_MANPOWER
        }
    }

    static class Workday
    {
        private int id;
        private DayOfWeek weekday;
        private LocalTime openingTime;
        private LocalTime closingTime;
        private int puSlots;

        Workday()
        {
        }

        Workday(final DayOfWeek weekday, final LocalTime openingTime, final LocalTime closingTime, final int puSlots)
        {
            this.weekday = weekday;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.puSlots = puSlots;
        }

        int getId()
        {
            return this.id;
        }

        DayOfWeek getWeekday()
        {
            return this.weekday;
        }

        LocalTime getOpeningTime()
        {
            return this.openingTime;
        }

        LocalTime getClosingTime()
        {
            return this.closingTime;
        }

        int getPuSlots()
        {
            return this.puSlots;
        }
    }



}
