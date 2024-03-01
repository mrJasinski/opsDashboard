package com.opsDashboard.full;

class FullRefund
{
    private String country;
    private String stockId;
    private String link;
    private Status status;

    FullRefund(final String country, final String stockId, final String link, final Status status)
    {
        this.country = country;
        this.stockId = stockId;
        this.link = link;
        this.status = status;
    }

    enum Status
    {
        HQ_PRICING
        , HOO_APPROVAL
        , MD_APPROVAL
        , TO_BE_SEND
    }

    public String getCountry()
    {
        return country;
    }

    public String getStockId()
    {
        return stockId;
    }

    public String getLink()
    {
        return link;
    }

    public Status getStatus()
    {
        return status;
    }
}
