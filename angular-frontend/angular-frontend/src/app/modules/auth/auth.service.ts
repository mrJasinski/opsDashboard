import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";
import { BehaviorSubject, tap } from "rxjs";
import { User } from "./user.model";
import { Token } from "./token.model";
import { Router } from "@angular/router";

interface ResponseUser
{
    email : string;
}


@Injectable({ providedIn: 'root' })
export class AuthService
{
    user = new BehaviorSubject<User>(null);
    token = new BehaviorSubject<Token>(null);

    constructor(private http: HttpClient, private router: Router)
    {

    }

    signUp(email: string, password: string)
    {
        return this.http.post(AppConstants.APP_URL + AppConstants.REGISTER_API_URL
            , { 
                email : email
                , password : password
            });
    }

    signIn(email: string, password: string)
    {
        return this.http.post(
            AppConstants.APP_URL + AppConstants.AUTH_API_URL, 
            { 
                email : email
                , password : password
            }
            , {responseType: 'text'})
             .pipe(tap(resData => 
                {
                    const tokenValue = new Token(resData);
                    this.token.next(tokenValue);

                    localStorage.setItem('tokenData', JSON.stringify(tokenValue));
                 })); 
    }

    autoLogin()
    {
       const tokenData = localStorage.getItem('tokenData');

       if (!tokenData)
        return;

        const loadedToken = new Token(tokenData);

        this.token.next(loadedToken);
    }

    signOut()
    {
        this.token.next(null);
        localStorage.removeItem('tokenData');
        this.router.navigate(['/authenticate']);

        console.log(this.token);
    }
}