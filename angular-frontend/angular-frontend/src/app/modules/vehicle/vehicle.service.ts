import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";



@Injectable({providedIn: 'root'})
export class VehicleService
{
    stockId : string;

    constructor(private http: HttpClient)
    {

    }

    // getVehicle(stockId : string)
    // {  
    //   return this.http.get(AppConstants.APP_URL + AppConstants.VEHICLE_URL + '/' + stockId, { observe: 'response' });       
    // }

    getVehicleByStockId(stockId : string)
    {
        this.stockId = stockId;

        return this.http.get(AppConstants.APP_URL + AppConstants.VEHICLE_URL, {params : {stockId}, observe : 'response'});
    }
}
