package com.opsDashboard.user.dto;

import com.opsDashboard.claim.ClaimStatus;
import com.opsDashboard.full.FRStatus;
import com.opsDashboard.specialAccess.SAStatus;
import com.opsDashboard.utils.Country;

import java.util.Set;

public class RoleDTO
{
    private String type;
    private Set<Country> countries;
    private Set<ClaimStatus> claimStatuses;
    private Set<SAStatus> sAStatuses;
    private Set<FRStatus> fRStatuses;


    RoleDTO(final Set<Country> countries, final Set<SAStatus> sAStatuses)
    {
        this.countries = countries;
        this.sAStatuses = sAStatuses;
    }

    public RoleDTO(final String type, final Set<Country> countries)
    {
        this.type = type;
        this.countries = countries;
    }

    RoleDTO(final String type, final Set<Country> countries, final Set<SAStatus> sAStatuses)
    {
        this(countries, sAStatuses);
        this.type = type;
    }

    public RoleDTO(
            final String type
            , final Set<Country> countries
            , final Set<ClaimStatus> claimStatuses
            , final Set<SAStatus> sAStatuses
            , final Set<FRStatus> fRStatuses)
    {
        this(type, countries, sAStatuses);
        this.claimStatuses = claimStatuses;
        this.fRStatuses = fRStatuses;
    }

    public String getType()
    {
        return this.type;
    }

    public Set<Country> getCountries()
    {
        return this.countries;
    }

    public Set<SAStatus> getSAStatuses()
    {
        return this.sAStatuses;
    }

    public Set<ClaimStatus> getClaimStatuses()
    {
        return this.claimStatuses;
    }

    public Set<FRStatus> getFRStatuses()
    {
        return this.fRStatuses;
    }
}
