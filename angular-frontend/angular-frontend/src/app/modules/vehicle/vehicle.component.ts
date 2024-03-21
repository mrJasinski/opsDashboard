import { Component } from "@angular/core";
import { VehicleService } from "./vehicle.service";
import { Vehicle } from "./vehicle.model";


@Component({
    selector : 'vehicle-view'
    , templateUrl : './vehicle.component.html'
})
export class VehicleComponent
{
    vehicle : Vehicle;

    constructor(vehicleService : VehicleService)
    {
        
    }
}



// @Component({
//     selector: 'sa-pending',
//     templateUrl: './sa.component.html'
//   })
//   export class SAComponent implements OnInit 
//   {
//     specialAccesses = new Array();
//     selected : string;

//     constructor (private saService: SAService) 
//     {
//       if (saService.filter != null)
//         this.selected = saService.filter;
//       else
//         this.selected = 'PENDING';
//     }

//     ngOnInit(): void 
//     {
//       this.onChangeFilter(this.selected);
//     }

//     onChangeFilter(filter : string)
//     {
//       this.saService.getSpecialAccess(filter).subscribe(responseData => {this.specialAccesses = <any> responseData.body});
//     }
// }