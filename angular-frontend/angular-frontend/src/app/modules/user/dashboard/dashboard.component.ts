import { Component, OnInit } from '@angular/core';
import { DashboardService } from './dashboard.service';
import { Dashboard } from './dashboard.model';
import { SpecialAccess } from './sa.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  dash : Dashboard;
  fullRefunds = new Array();
  specialAccesses = new Array();

  constructor (private dashboardService: DashboardService) {}

  ngOnInit() 
  {
    this.dashboardService.getDashboard().subscribe(responseData => 
      { this.dash = <any>responseData.body}); 
        
    this.dashboardService.getFullRefunds().subscribe(responseData =>
        { this.fullRefunds = <any> responseData.body}
      );

      this.dashboardService.getSpecialAccesses().subscribe(responseData =>
        {
            this.specialAccesses = <any> responseData.body
        });
  }

  onApproveSA(isApproved : boolean, stockId : string)
  {
      this.dashboardService.approveSA(isApproved, stockId).subscribe(responseData =>
        {
            this.specialAccesses = <any> responseData.body
        });;
  }

}
