export class SpecialAccess
{
    private country : string;
    private stockId : string;
    private link : string;
    private reason : string;
    private explanation : string;
    private transportMethod : string;
    private pickupDate : Date;
    private status : string;

    constructor(country : string, 
        stockId : string, 
         link : string, 
         reason : string, 
         explanation : string, 
         transportMethod : string, 
         pickupDate : Date,
         status : string)
         {
            this.country = country;
            this.stockId = stockId;
            this.link = link;
            this.reason = reason;
            this.explanation = explanation;
            this.transportMethod =transportMethod;
            this.pickupDate = pickupDate;
            this.status = status
         }
}