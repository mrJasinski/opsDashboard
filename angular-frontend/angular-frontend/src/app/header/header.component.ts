import { Component, OnDestroy, OnInit } from "@angular/core";
import { AuthService } from "../modules/auth/auth.service";
import { Subscription } from "rxjs";

@Component({
    selector: 'app-header'
    , templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit, OnDestroy
{
    isAuthenticated = false;
    private tokenSub: Subscription;
    searchedStockId: string;

    constructor(private authService: AuthService)
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

    onSearch(searchedStockId : string)
    {
        // TODO przejście do wyszukanego pojazdu lub błąd
    }
}