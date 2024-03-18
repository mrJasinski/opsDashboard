package com.opsDashboard.user;

import com.opsDashboard.claim.ClaimStatus;
import com.opsDashboard.full.FRStatus;
import com.opsDashboard.specialAccess.SAStatus;
import com.opsDashboard.utils.Country;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

class User
{
    private int id;
    private String email;
    private String password;
    private Role role;
    private boolean isAvailable;
    private final Set<Workday> workdays = new HashSet<>();

    User()
    {
    }

    User(final String email, final String password)
    {
        this.email = email;
        this.password = password;
    }

    User(final String email, final String password, final Role role)
    {
        this(email, password);
        this.role = role;
    }

    User(final String email, final String password, final Role role, final boolean isAvailable)
    {
        this(email, password, role);
        this.isAvailable = isAvailable;
    }

    User(final int id, final String email, final String password)
    {
        this(email, password);
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public Role getRole()
    {
        return this.role;
    }

    public void changeAvailable()
    {
        this.isAvailable = !isAvailable;
    }

    public Set<Workday> getWorkdays()
    {
        return this.workdays;
    }

    void addWorkday(final WorkLocation location, final LocalDate date, final LocalTime startTime)
    {
        this.workdays.add(new Workday(date, startTime, location));
    }

    void restartWorkday(final Workday workday, final LocalTime restartTime)
    {
        workday.setRestartTime(restartTime);
    }

    Workday getWorkDayByDate(final LocalDate date)
    {
        var days = this.workdays
                .stream()
                .filter(w -> (w.getWorkday()).equals(date))
                .toList();

        if (days.isEmpty())
            throw new NoSuchElementException("Workday for given day not found!");

        return days.get(0);
    }

    static class Workday
    {
        private int id;
        private LocalDate workday;
        private LocalTime startTime;
        private LocalTime restartTime;
        private int workTimeMins = 0;
        private WorkLocation location;

        Workday()
        {
        }

        Workday(final LocalDate workday, final LocalTime startTime, final WorkLocation location)
        {
            this.workday = workday;
            this.startTime = startTime;
            this.location = location;
        }

        Workday(final LocalDate workday, final LocalTime startTime, final LocalTime restartTime, final int workTimeMins, final WorkLocation location)
        {
            this(workday, startTime, location);
            this.restartTime = restartTime;
        }

        void addWorkTimeMins(final int minutes)
        {
            this.workTimeMins += minutes;
        }

        public int getId()
        {
            return this.id;
        }

        public LocalDate getWorkday()
        {
            return this.workday;
        }

        public LocalTime getStartTime()
        {
            return this.startTime;
        }

        public LocalTime getRestartTime()
        {
            return this.restartTime;
        }

        void setRestartTime(final LocalTime restartTime)
        {
            this.restartTime = restartTime;
        }

        public int getWorkTimeMins()
        {
            return this.workTimeMins;
        }

        public WorkLocation getLocation()
        {
            return this.location;
        }
    }

    static class Role
    {
        private int id;
        private Type type;
        private String countries;
        private String claimStatuses;
        private String sAStatuses;
        private String fRStatuses;

        Role()
        {
        }

        Role(final Type type, final Set<Country> countries)
        {
            this.type = type;
            this.countries = wrapCountries(countries);
        }

        Role(final Type type
                , final Set<Country> countries
                , final Set<ClaimStatus> claimStatuses
                , final Set<SAStatus> sAStatuses
                , final Set<FRStatus> fRStatuses)
        {
            this.type = type;
            this.countries = wrapCountries(countries);
            this.claimStatuses = wrapClaimStatuses(claimStatuses);
            this.sAStatuses = wrapSAStatuses(sAStatuses);
            this.fRStatuses = wrapFRStatuses(fRStatuses);
        }

        String wrapCountries(Set<Country> countries)
        {
            var result = new StringBuilder();

            for (final Country country : countries)
                result.append(country).append(" ");

            return result.toString();
        }

        Set<Country> unwrapCountries(String countries)
        {
            var countriesSplit = countries.split(" ");

            var enums = new Country[countriesSplit.length];

            for (int i = 0; i < countriesSplit.length; i++)
                enums[i] = Country.valueOf(countriesSplit[i]);

            return Set.of(enums);
        }

        String wrapClaimStatuses(Set<ClaimStatus> statuses)
        {
            var result = new StringBuilder();

            for (final ClaimStatus status : statuses)
                result.append(status).append(" ");

            return result.toString();
        }

        Set<ClaimStatus> unwrapClaimStatuses(String statuses)
        {
            var statusesSplit = statuses.split(" ");

            var enums = new ClaimStatus[statusesSplit.length];

            for (int i = 0; i < statusesSplit.length; i++)
                enums[i] = ClaimStatus.valueOf(statusesSplit[i]);

            return Set.of(enums);
        }

        String wrapSAStatuses(Set<SAStatus> statuses)
        {
            var result = new StringBuilder();

            for (final SAStatus status : statuses)
                result.append(status).append(" ");

            return result.toString();
        }

        Set<SAStatus> unwrapSAStatuses(String statuses)
        {
            var statusesSplit = statuses.split(" ");

            var enums = new SAStatus[statusesSplit.length];

            for (int i = 0; i < statusesSplit.length; i++)
                enums[i] = SAStatus.valueOf(statusesSplit[i]);

            return Set.of(enums);
        }

        String wrapFRStatuses(Set<FRStatus> statuses)
        {
            var result = new StringBuilder();

            for (final FRStatus status : statuses)
                result.append(status).append(" ");

            return result.toString();
        }

        Set<FRStatus> unwrapFRStatuses(String statuses)
        {
            var statusesSplit = statuses.split(" ");

            var enums = new FRStatus[statusesSplit.length];

            for (int i = 0; i < statusesSplit.length; i++)
                enums[i] = FRStatus.valueOf(statusesSplit[i]);

            return Set.of(enums);
        }

        public int getId()
        {
            return this.id;
        }

        public Type getType()
        {
            return this.type;
        }

        public String getCountries()
        {
            return this.countries;
        }

        String getClaimStatuses()
        {
            return this.claimStatuses;
        }

        String getSAStatuses()
        {
            return this.sAStatuses;
        }

        String getFRStatuses()
        {
            return this.fRStatuses;
        }

        Set<Country> getCountriesSet()
        {
            return unwrapCountries(this.countries);
        }

        Set<ClaimStatus> getClaimStatusesSet()
        {
            return unwrapClaimStatuses(this.claimStatuses);
        }

        Set<SAStatus> getSAStatusesSet()
        {
            return unwrapSAStatuses(this.sAStatuses);
        }

        Set<FRStatus> getFRStatusesSet()
        {
            return unwrapFRStatuses(this.fRStatuses);
        }

        enum Type
        {
            TEAM_LEADER
            , DECISION_MAKER
            , OPS_AGENT
            , DEALER_SERVICE_AGENT
        }
    }
}
