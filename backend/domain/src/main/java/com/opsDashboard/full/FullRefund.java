package com.opsDashboard.full;

class FullRefund
{
    private String country;
    private String stockId;
    private String link;
    private FRStatus status;

    FullRefund(final String country, final String stockId, final String link, final FRStatus status)
    {
        this.country = country;
        this.stockId = stockId;
        this.link = link;
        this.status = status;
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

    public FRStatus getStatus()
    {
        return status;
    }
}
