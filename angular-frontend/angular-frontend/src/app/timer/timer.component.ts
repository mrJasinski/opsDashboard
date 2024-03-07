import { Component, Input } from "@angular/core";
import { map, takeWhile, timer } from "rxjs";
import { AuthService } from "../modules/auth/auth.service";

@Component({
    selector: 'worktime'
    , templateUrl: './timer.component.html'
})
export class WorkTimeComponent
{
    constructor(private authService : AuthService)
    {
    }

    @Input() hours = 7 * 60 * 60 - this.authService.getMins();

    timeRemaining$ = timer(0, 1000)
        .pipe(map(n => (this.hours - n) * 1000), takeWhile(n => n >= 0));
    
}