package com.opsDashboard.specialAccess.dto;

import com.opsDashboard.specialAccess.SAReason;
import com.opsDashboard.specialAccess.SAStatus;
import com.opsDashboard.utils.Country;
import com.opsDashboard.utils.TransportMethod;

import java.time.LocalDate;

public class SpecialAccessDTO
{
    private Country country;
    private String stockId;
    private String link;
    private SAReason reason;
    private String explanation;
    private TransportMethod transportMethod;
    private LocalDate pickupDate;
    private SAStatus status;
    private boolean pending;

     public SpecialAccessDTO(
             final Country country
             , final String stockId
             , final String link
             , final SAReason reason
             , final String explanation
             , final TransportMethod transportMethod
             , final LocalDate pickupDate
             , final SAStatus status
             , final boolean pending)
    {
        this.country = country;
        this.stockId = stockId;
        this.link = link;
        this.reason = reason;
        this.explanation = explanation;
        this.transportMethod = transportMethod;
        this.pickupDate = pickupDate;
        this.status = status;
        this.pending = pending;
    }

    public Country getCountry()
    {
        return this.country;
    }

    public String getStockId()
    {
        return this.stockId;
    }

    public String getLink()
    {
        return this.link;
    }

    public SAReason getReason()
    {
        return this.reason;
    }

    public String getExplanation()
    {
        return this.explanation;
    }

    public TransportMethod getTransportMethod()
    {
        return this.transportMethod;
    }

    public LocalDate getPickupDate()
    {
        return this.pickupDate;
    }

    public SAStatus getStatus()
    {
        return this.status;
    }

    public boolean isPending()
    {
        return this.pending;
    }
}
