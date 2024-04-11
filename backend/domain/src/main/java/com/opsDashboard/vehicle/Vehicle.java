package com.opsDashboard.vehicle;

import com.opsDashboard.vo.LCSource;
import com.opsDashboard.vo.MerchantSource;

class Vehicle
{
    private int id;
    private String stockId;
    private String manufacturer;
    private String model;
    private MerchantSource merchant;
    private LCSource location;

    Vehicle()
    {
    }

    Vehicle(final String stockId
            , final String manufacturer
            , final String model
            , final MerchantSource merchant)
    {
        this.stockId = stockId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.merchant = merchant;
    }

    public int getId()
    {
        return this.id;
    }

    public String getStockId()
    {
        return this.stockId;
    }

    public String getManufacturer()
    {
        return this.manufacturer;
    }

    public String getModel()
    {
        return this.model;
    }

    public MerchantSource getMerchant()
    {
        return this.merchant;
    }

    LCSource getLocation()
    {
        return this.location;
    }
}
