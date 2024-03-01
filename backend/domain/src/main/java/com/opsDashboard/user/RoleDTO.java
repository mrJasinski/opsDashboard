package com.opsDashboard.user;

import com.opsDashboard.specialAccess.Status;
import com.opsDashboard.utils.Country;

import java.util.Set;

public class RoleDTO
{
    private String type;
    private Set<Country> countries;
    private Set<Status> saStatuses;

    RoleDTO(final Set<Country> countries, final Set<Status> saStatuses)
    {
        this.countries = countries;
        this.saStatuses = saStatuses;
    }

    public String getType()
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
}
