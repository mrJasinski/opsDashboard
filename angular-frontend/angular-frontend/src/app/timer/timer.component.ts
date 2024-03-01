import { Component, Input } from "@angular/core";
import { map, takeWhile, timer } from "rxjs";

@Component({
    selector: 'worktime'
    , templateUrl: './timer.component.html'
})
export class WorkTimeComponent
{
    endTime : Date;

    @Input() hours = 7 * 60 * 60;

    timeRemaining$ = timer(0, 1000)
        .pipe(map(n => (this.hours - n) * 1000), takeWhile(n => n >= 0));
    
}