export class Dashboard
{
    pendingClaimCount: number;
    pendingSACount: number;
    pendingFRCount: number;

    constructor(pendingClaimCount: number, pendingSACount: number, pendingFRCount: number)
    {
        this.pendingClaimCount = pendingClaimCount;
        this.pendingSACount = pendingSACount;
        this.pendingFRCount = pendingFRCount;
    }
}