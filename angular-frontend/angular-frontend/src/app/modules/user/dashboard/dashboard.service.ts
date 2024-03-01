import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../../constans/app.constans";
import { AuthService } from "../../auth/auth.service";
import { exhaustMap, take } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class DashboardService 
  {
    constructor(private http: HttpClient, private authService: AuthService) { }

    getDashboard()
    {  
          return this.http.get(AppConstants.APP_URL + AppConstants.DASHBOARD_URL, { observe: 'response' });       
    }

    getFullRefunds()
    {
      return this.http.get(AppConstants.APP_URL + AppConstants.FR_URL, { observe: 'response' });
    }

    getSpecialAccesses()
    {
      return this.http.get(AppConstants.APP_URL + AppConstants.SA_URL, { observe: 'response' });
    }

    approveSA(isApproved : boolean, stockId : string)
    {
        console.log(AppConstants.APP_URL + '/SAStatusChange', {params: {isApproved, stockId}});

      return this.http.get(AppConstants.APP_URL + '/SAStatusChange', {params: {isApproved, stockId}, observe: 'response'});

    }
    
  }