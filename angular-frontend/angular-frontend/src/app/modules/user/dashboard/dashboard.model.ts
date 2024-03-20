export class Dashboard
{
    pendingClaimCount: number;
    pendingSACount: number;
    ongoingSACount: number;
    pendingFRCount: number;

    constructor(pendingClaimCount: number, pendingSACount: number, ongoingSANumber, pendingFRCount: number)
    {
        this.pendingClaimCount = pendingClaimCount;
        this.pendingSACount = pendingSACount;
        this.ongoingSACount = ongoingSANumber;
        this.pendingFRCount = pendingFRCount;
    }
}