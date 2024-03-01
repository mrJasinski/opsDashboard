import { Component, OnInit } from '@angular/core';
import { AuthService } from './modules/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit 
{
  title = 'OpsDashboard';

  constructor(private authService: AuthService)
  {
  }

  ngOnInit()
  {
      this.authService.autoLogin();
  }
}
