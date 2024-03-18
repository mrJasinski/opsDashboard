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
    minsLeft : number;

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

    signIn(email: string, password: string, location: string)
    {
        return this.http.post<User>(
            AppConstants.APP_URL + AppConstants.AUTH_API_URL, 
            { 
                email : email
                , password : password
                , location : location
            })
             .pipe(tap(resData => 
                {
                    console.log(resData.token);

                    const user = new User(resData.email, resData.token, resData.minutesLeft);

                    this.minsLeft = user.minutesLeft;
                     
                    const tokenValue = new Token(user.token);

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
        this.http.get(AppConstants.APP_URL + AppConstants.WORKDAY_END_URL).subscribe();
        
        this.token.next(null);
        localStorage.removeItem('tokenData');
        this.router.navigate(['/authenticate']);
    }

    getMins()
    {
        return this.minsLeft * 60;
    }
}