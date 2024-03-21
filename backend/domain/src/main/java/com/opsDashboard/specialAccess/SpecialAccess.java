package com.opsDashboard.specialAccess;

import com.opsDashboard.utils.Country;
import com.opsDashboard.utils.TransportMethod;
import com.opsDashboard.vo.VehicleSource;

import java.time.LocalDate;

class SpecialAccess
{
    private int id;
    private Country country;
    private VehicleSource vehicle;
    private String link;
    private SAReason reason;
    private String explanation;
    private TransportMethod transportMethod;
    private LocalDate pickupDate;
    private SAStatus status;

    SpecialAccess()
    {
    }

    SpecialAccess(final Country country, final VehicleSource vehicle, final String link, final SAReason reason, final String explanation
            , final TransportMethod transportMethod, final LocalDate pickupDate, final SAStatus status)
    {
        this.country = country;
        this.vehicle = vehicle;
        this.link = link;
        this.reason = reason;
        this.explanation = explanation;
        this.transportMethod = transportMethod;
        this.pickupDate = pickupDate;
        this.status = status;
    }

    SpecialAccess(final int id, final Country country, final VehicleSource vehicle, final String link, final SAReason reason, final String explanation
            , final TransportMethod transportMethod, final LocalDate pickupDate, final SAStatus status)
    {
        this(country, vehicle, link, reason, explanation, transportMethod, pickupDate, status);
        this.id = id;
    }

    void changeStatus(final SAStatus SAStatus, final boolean isApproved)
    {
        switch (SAStatus)
        {
            case CREATED ->
            {
                if (isApproved)
                    this.status = SAStatus.WAITING_FOR_LOCAL_APPROVAL;
            }

            case WAITING_FOR_LOCAL_APPROVAL ->
            {
                if (isApproved)
                    this.status = SAStatus.WAITING_FOR_HQ_APPROVAL;

                if (!isApproved)
                    this.status = SAStatus.LOCAL_REJECTED;
            }

            case WAITING_FOR_HQ_APPROVAL ->
            {
                if (isApproved)
                    this.status = SAStatus.GRANTED;

                if (!isApproved)
                    this.status = SAStatus.HQ_REJECTED;
            }
        }
    }

    public int getId()
    {
        return this.id;
    }

    public Country getCountry()
    {
        return this.country;
    }

    public VehicleSource getVehicle()
    {
        return this.vehicle;
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

}

