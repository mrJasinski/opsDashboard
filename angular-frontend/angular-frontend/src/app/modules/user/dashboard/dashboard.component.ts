import { Component, OnInit } from '@angular/core';
import { DashboardService } from './dashboard.service';
import { Dashboard } from './dashboard.model';
import { SAService } from '../../sa/sa.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  dash : Dashboard;
  // fullRefunds = new Array();
  

  constructor (private dashboardService: DashboardService, private saService: SAService) {}

  ngOnInit() 
  {
   this.dashboardService.getDashboard()
    .subscribe(response => 
      { this.dash = <any>response.body}); 
        
    // this.dashboardService.getFullRefunds().subscribe(responseData =>
    //     { this.fullRefunds = <any> responseData.body}
    //   );

  }

  onSetFilter(filter : string)
  {
      this.saService.filter = filter;
  }

  // onApproveSA(isApproved : boolean, stockId : string)
  // {
  //     this.dashboardService.approveSA(isApproved, stockId).subscribe(responseData =>
  //       {
  //           this.specialAccesses = <any> responseData.body
  //       });;
  // }

}
