package com.opsDashboard.user;

import com.opsDashboard.specialAccess.SAStatus;
import com.opsDashboard.utils.Country;
import com.opsDashboard.vo.ClaimSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
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

    void restartWorkday(final LocalDate date, final LocalTime restartTime)
    {
        var workday = this.workdays.stream().filter(w -> (w.workday).equals(date)).toList().get(0);

        workday.setRestartTime(restartTime);
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
//        private Set<SAStatus> saStatuses;

        Role()
        {
        }

        Role(final Type type, final Set<Country> countries)
        {
            this.type = type;
            this.countries = wrapCountries(countries);
        }

//        Role(final Type type, final Set<Country> countries, final Set<SAStatus> saStatuses)
//        {
//            this.type = type;
//            this.countries = countries;
//            this.saStatuses = saStatuses;
//        }

        String wrapCountries(Set<Country> countries)
        {
            var result = new StringBuilder();

            for (final Country country : countries)
            {
                result.append(country).append(" ");
            }

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

//        public Set<SAStatus> getSaStatuses()
//        {
//            return this.saStatuses;
//        }

        enum Type
        {
            TL_OPS_PL
            , TL_OPS_BALTICS
            , TL_OPS_BALKANS
            , TL_OPS_CZSK
            , DM_PL
            , DM_CEE
        }
    }
}
