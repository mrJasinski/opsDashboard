package com.opsDashboard.ticket.dto;

import java.util.Set;

public class TicketWriteModel
{
    private String creatorMail;
    private String title;
    private String category;
    private String subCategory;
    private String stockId;
    private String content;
    private Set<String> ccMails;

    public String getCreatorMail()
    {
        return this.creatorMail;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getCategory()
    {
        return this.category;
    }

    public String getSubCategory()
    {
        return this.subCategory;
    }

    public String getStockId()
    {
        return this.stockId;
    }

    public String getContent()
    {
        return this.content;
    }

    public Set<String> getCcMails()
    {
        return this.ccMails;
    }
}
