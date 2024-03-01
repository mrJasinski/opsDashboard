package com.opsDashboard.user;

import com.opsDashboard.specialAccess.Status;
import com.opsDashboard.utils.Country;

import java.util.Set;

class User
{
    private int id;
    private String email;
    private String password;
    private Role role;

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

    static class Role
    {
        private int id;
        private Type type;
        private Set<Country> countries;
        private Set<Status> saStatuses;

        Role()
        {
        }

        Role(final Type type, final Set<Country> countries)
        {
            this.type = type;
            this.countries = countries;
        }

        Role(final Type type, final Set<Country> countries, final Set<Status> saStatuses)
        {
            this.type = type;
            this.countries = countries;
            this.saStatuses = saStatuses;
        }

        public int getId()
        {
            return this.id;
        }

        public Type getType()
        {
            return this.type;
        }

        public Set<Country> getCountries()
        {
            return this.countries;
        }

        public Set<Status> getSaStatuses()
        {
            return this.saStatuses;
        }

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
