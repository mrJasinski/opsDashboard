import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take } from "rxjs";
import { AuthService } from "./auth.service";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate
{
    constructor(private authService: AuthService, private router: Router)
    {

    }

    canActivate(route: ActivatedRouteSnapshot, router: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> 
    {
        return this.authService.token.pipe(
            take(1)
            , map(token => 
            { 
                const isAuth= !!token;

                if (isAuth)
                    return true;

                return this.router.createUrlTree(['/authenticate']);
            }));
    }
}