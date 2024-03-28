import { Component, OnDestroy, OnInit } from "@angular/core";
import { AuthService } from "../modules/auth/auth.service";
import { Subscription } from "rxjs";
import { VehicleService } from "../modules/vehicle/vehicle.service";
import { Router } from "@angular/router";
import { NgModel } from "@angular/forms";

@Component({
    selector: 'app-header'
    , templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit, OnDestroy
{
    isAuthenticated = false;
    private tokenSub: Subscription;
    searchedStockId: string;
    searchId: any;

    constructor(private authService: AuthService, private vehicleService : VehicleService, private router: Router)
    {

    }

    ngOnInit()
    {
        this.tokenSub = this.authService.token.subscribe(token => 
        {
            this.isAuthenticated = !!token;
        });
    }

    ngOnDestroy()
    {
        this.tokenSub.unsubscribe();
    }

    onLogout()
    {
        this.authService.signOut();
    }

    onSearch(stockId : string)
    {
            // TODO przejście do wyszukanego pojazdu lub błąd
        this.vehicleService.stockId = stockId;

        this.router.navigate(['/vehicle', stockId]);

        this.searchId = null;
    }
}