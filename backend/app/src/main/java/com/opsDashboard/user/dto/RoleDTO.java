package com.opsDashboard.user.dto;

import com.opsDashboard.claim.ClaimStatus;
import com.opsDashboard.specialAccess.SAStatus;
import com.opsDashboard.utils.Country;

import java.util.Set;

public class RoleDTO
{
    private String type;
    private Set<Country> countries;
    private Set<SAStatus> saStatuses;
    private Set<ClaimStatus> claimStatuses;

    RoleDTO(final Set<Country> countries, final Set<SAStatus> saStatuses)
    {
        this.countries = countries;
        this.saStatuses = saStatuses;
    }

    public RoleDTO(final String type, final Set<Country> countries)
    {
        this.type = type;
        this.countries = countries;
    }

    RoleDTO(final String type, final Set<Country> countries, final Set<SAStatus> saStatuses)
    {
        this(countries, saStatuses);
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public Set<Country> getCountries()
    {
        return this.countries;
    }

    public Set<SAStatus> getSaStatuses()
    {
        return this.saStatuses;
    }

    public Set<ClaimStatus> getClaimStatuses()
    {
        return this.claimStatuses;
    }
}
