package com.opsDashboard.full;

import com.opsDashboard.vo.VehicleSource;

class FullRefund
{
    private int id;
    private String link;
    private FRStatus status;
    private VehicleSource vehicle;

    FullRefund()
    {
    }

    FullRefund(final String link, final FRStatus status, final VehicleSource vehicle)
    {
        this.link = link;
        this.status = status;
        this.vehicle = vehicle;
    }

    public String getLink()
    {
        return link;
    }

    public FRStatus getStatus()
    {
        return status;
    }

    public VehicleSource getVehicle()
    {
        return this.vehicle;
    }
}
