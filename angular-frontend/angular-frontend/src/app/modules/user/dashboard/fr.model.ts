export class fullRefund
{
    private country : string;
    private stockId : string;
    private link : string;
    private status : string;

    constructor(country : string, stockId : string, link : string,  status : string)
    {
        this.country = country;
        this.stockId = stockId;
        this.link = link;
        this.status = status;
    }
}