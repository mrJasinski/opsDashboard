import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";



@Injectable({providedIn: 'root'})
export class VehicleService
{
    constructor(private http: HttpClient)
    {

    }

    // getVehicle(stockId : string)
    // {  
    //   return this.http.get(AppConstants.APP_URL + AppConstants.VEHICLE_URL + '/' + stockId, { observe: 'response' });       
    // }
}
