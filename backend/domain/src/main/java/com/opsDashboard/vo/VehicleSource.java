package com.opsDashboard.vo;

public class VehicleSource
{
    private String stockId;

    VehicleSource()
    {
    }

    public VehicleSource(final String stockId)
    {
        this.stockId = stockId;
    }

     public String getStockId()
    {
        return this.stockId;
    }
}
