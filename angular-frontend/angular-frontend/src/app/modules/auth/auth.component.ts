import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { User } from './user.model';

@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit 
{
  isLoginMode = true;

  constructor(private authService: AuthService, private router: Router)
  {

  }

  onSwitchMode()
  {
    this.isLoginMode = !this.isLoginMode;
  }

 ngOnInit(): void {

 }

 onSubmit(form: NgForm)
 {
  if (!form.valid)
  {
    return;
  }

  const email = form.value.email;
  const password = form.value.password;

  if (this.isLoginMode)
  {
    this.authService.signIn(email, password) .subscribe(resData => 
      {
          this.router.navigate(['/dashboard']);
    }); 
  }

  if (!this.isLoginMode)
  {
    this.authService.signUp(email, password).subscribe(resData => 
      {
         /* this.router.navigate(['/dashboard']); */
         console.log(resData);
    });
  }

  

  form.reset();

 }

}