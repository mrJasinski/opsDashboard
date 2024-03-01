package com.opsDashboard.specialAccess;

import com.opsDashboard.utils.Country;
import com.opsDashboard.vo.UserSource;

import java.time.LocalDate;
import java.util.Set;

class SpecialAccess
{
    private int id;
    private Country country;
    private String stockId;
    private String link;
    private Reason reason;
    private String explanation;
    private TransportMethod transportMethod;
    private LocalDate pickupDate;
    private Status status;

    SpecialAccess()
    {
    }

    SpecialAccess(final Country country, final String stockId, final String link, final Reason reason, final String explanation
            , final TransportMethod transportMethod, final LocalDate pickupDate, final Status status)
    {
        this.country = country;
        this.stockId = stockId;
        this.link = link;
        this.reason = reason;
        this.explanation = explanation;
        this.transportMethod = transportMethod;
        this.pickupDate = pickupDate;
        this.status = status;
    }

    SpecialAccess(final int id, final Country country, final String stockId, final String link, final Reason reason, final String explanation
            , final TransportMethod transportMethod, final LocalDate pickupDate, final Status status)
    {
        this(country, stockId, link, reason, explanation, transportMethod, pickupDate, status);
        this.id = id;
    }

    void changeStatus(final Status status, final boolean isApproved)
    {
        switch (status)
        {
            case CREATED ->
            {
                if (isApproved)
                    this.status = Status.WAITING_FOR_LOCAL_APPROVAL;
            }

            case WAITING_FOR_LOCAL_APPROVAL ->
            {
                if (isApproved)
                    this.status = Status.WAITING_FOR_HQ_APPROVAL;

                if (!isApproved)
                    this.status = Status.LOCAL_REJECTED;
            }

            case WAITING_FOR_HQ_APPROVAL ->
            {
                if (isApproved)
                    this.status = Status.GRANTED;

                if (!isApproved)
                    this.status = Status.HQ_REJECTED;
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

    public String getStockId()
    {
        return this.stockId;
    }

    public String getLink()
    {
        return this.link;
    }

    public Reason getReason()
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

    public Status getStatus()
    {
        return this.status;
    }

    enum TransportMethod
    {
        PICK_UP
        , TRANSPORT
        , NOT_PICKED_UP
    }

    static class Reason
    {
        private int id;
        private String name;

        Reason()
        {
        }

        Reason(final String name)
        {
            this.name = name;
        }

        public int getId()
        {
            return this.id;
        }

        public String getName()
        {
            return this.name;
        }
    }
}

