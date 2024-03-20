package com.opsDashboard.user.dto;

public class Dashboard
{
    private int pendingClaimCount;
    private int pendingSACount;
    private int ongoingSACount;
    private int pendingFRCount;

    public Dashboard(final int pendingClaimCount, final int pendingSACount, final int pendingFRCount)
    {
        this.pendingClaimCount = pendingClaimCount;
        this.pendingSACount = pendingSACount;
        this.pendingFRCount = pendingFRCount;
    }

    public Dashboard(final int pendingClaimCount
            , final int pendingSACount
            , final int ongoingSACount
            , final int pendingFRCount)
    {
        this.pendingClaimCount = pendingClaimCount;
        this.pendingSACount = pendingSACount;
        this.ongoingSACount = ongoingSACount;
        this.pendingFRCount = pendingFRCount;
    }

    public int getPendingClaimCount()
    {
        return this.pendingClaimCount;
    }

    public int getPendingSACount()
    {
        return this.pendingSACount;
    }

    public int getOngoingSACount()
    {
        return this.ongoingSACount;
    }

    public int getPendingFRCount()
    {
        return this.pendingFRCount;
    }
}
