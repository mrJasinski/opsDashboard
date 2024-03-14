package com.opsDashboard.user.dto;

public class Dashboard
{
    private int pendingClaimsCount;
    private int pendingSACount;
    private int pendingFRCount;

    public Dashboard(final int pendingClaimsCount, final int pendingSACount, final int pendingFRCount)
    {
        this.pendingClaimsCount = pendingClaimsCount;
        this.pendingSACount = pendingSACount;
        this.pendingFRCount = pendingFRCount;
    }

    public int getPendingClaimsCount()
    {
        return this.pendingClaimsCount;
    }

    public int getPendingSACount()
    {
        return this.pendingSACount;
    }

    public int getPendingFRCount()
    {
        return this.pendingFRCount;
    }
}
