import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HeaderComponent } from './header/header.component';
import { UserComponent } from './modules/user/user.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { DashboardComponent } from './modules/user/dashboard/dashboard.component';
import { AuthComponent } from './modules/auth/auth.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { WorkTimeComponent } from './timer/timer.component';
import { AuthInterceptorService } from './modules/auth/auth-interceptor.service';
import { AuthGuard } from './modules/auth/auth.guard';
import { ClaimComponent } from './modules/claim/claim.component';
import { SAComponent } from './modules/sa/sa.component';
import { VehicleComponent } from './modules/vehicle/vehicle.component';
import { ContactComponent } from './modules/contact/contact.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent }
  , { path: 'authenticate', component: AuthComponent}
  , { path: 'users', component: UserComponent }
  , { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] }
  , {path: 'claims', component: ClaimComponent, canActivate: [AuthGuard]}
  , {path: 'specialAccess', component: SAComponent, canActivate: [AuthGuard]}
  , {path : 'vehicle/:stockId', component: VehicleComponent, canActivate: [AuthGuard]}
  , {path : 'contact', component: ContactComponent}
]; 

@NgModule({
  declarations: [
    AppComponent
    , HeaderComponent
    , UserComponent
    , DashboardComponent
    , WorkTimeComponent
    , AuthComponent
    , SAComponent
    , VehicleComponent
    , ContactComponent
  ],
  imports: [
    BrowserModule
    , FormsModule
    , RouterModule.forRoot(appRoutes)
    , HttpClientModule
    , NgbModule
    , HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN',
    }),
  ],
  providers: [ {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true} ],
  bootstrap: [AppComponent]
})
export class AppModule { }
