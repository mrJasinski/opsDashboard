package com.opsDashboard.claim;

import com.opsDashboard.vo.UserSource;
import com.opsDashboard.vo.VehicleSource;

import java.time.LocalDate;

class Claim
{
    private int id;
    private int claimNumber;
    private String link;
    private ClaimStatus status;
    private VehicleSource vehicle;
    private LocalDate assignedDate;
    private UserSource assignedAgent;

    Claim()
    {
    }

    public Claim(final int number, final String link, final ClaimStatus status, final VehicleSource vehicle)
    {
        this.claimNumber = number;
        this.link = link;
        this.status = status;
        this.vehicle = vehicle;
    }

    Claim(
            final int claimNumber
            , final String link
            , final ClaimStatus status
            , final VehicleSource vehicle
            , final LocalDate assignedDate
            , final UserSource assignedAgent)
    {
        this.claimNumber = claimNumber;
        this.link = link;
        this.status = status;
        this.vehicle = vehicle;
        this.assignedDate = assignedDate;
        this.assignedAgent = assignedAgent;
    }



    void changeStatus()
    {

    }

    public int getId()
    {
        return this.id;
    }

    public int getClaimNumber()
    {
        return this.claimNumber;
    }

    public String getLink()
    {
        return this.link;
    }

    public ClaimStatus getStatus()
    {
        return this.status;
    }

    public VehicleSource getVehicle()
    {
        return this.vehicle;
    }

    public LocalDate getAssignedDate()
    {
        return this.assignedDate;
    }

    public UserSource getAssignedAgent()
    {
        return this.assignedAgent;
    }
}
