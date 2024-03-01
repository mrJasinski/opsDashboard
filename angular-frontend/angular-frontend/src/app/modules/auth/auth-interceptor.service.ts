import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { exhaustMap, take } from "rxjs";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor
{
    constructor(private authService: AuthService)
    {

    }

    intercept(request: HttpRequest<any>, next: HttpHandler)
    {
        return this.authService.token
        .pipe(take(1), exhaustMap(token => 
            {
                if (!token)
                    return next.handle(request);

                const requestWithHeader = request.clone(
                    {
                        headers: request.headers.set('Authorization', `Bearer ${token.token}`),
                    }
                );

                console.log(token.token);

                return next.handle(requestWithHeader);
            }));

        
    }
}