package com.opsDashboard.vehicle.dto;

import com.opsDashboard.vo.MerchantSource;

public class VehicleDTO
{
    private String stockId;
    private String manufacturer;
    private String model;
//    TODO merchantDTO?
    private MerchantSource merchant;

    public VehicleDTO(final String stockId, final String manufacturer, final String model)
    {
        this.stockId = stockId;
        this.manufacturer = manufacturer;
        this.model = model;
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
}
