package com.opsDashboard.claim.dto;

import com.opsDashboard.claim.ClaimStatus;
import com.opsDashboard.vo.VehicleSource;

public class ClaimWriteModel
{
    private String link;
    private ClaimStatus status;
    private VehicleSource vehicle;

    ClaimWriteModel()
    {
    }

    public ClaimWriteModel(final String link, final VehicleSource vehicle)
    {
        this.link = link;
        this.status = ClaimStatus.NEW;
        this.vehicle = vehicle;
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
}
