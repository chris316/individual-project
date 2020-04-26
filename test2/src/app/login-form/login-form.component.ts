import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

  model:User

  submitted = false;
  notLoggedIn=false;
  loggedIn=false;
  succeeded:boolean;

  constructor(public authService:AuthService){
    this.model=new User();
    this.submitted=false;
    this.notLoggedIn=false;
    this.loggedIn=false;
  }

  onSubmit(form: NgForm) { 
    if(form.invalid)
    {
      return;
    }
    this.authService.login(this.model.username,this.model.password);
    this.notLoggedIn=false;
    setTimeout(()=>{
    this.succeeded=this.authService.showIfCorrect();
    },1500)
    setTimeout(()=>{
    if(this.succeeded)
    {
      this.notLoggedIn=false;
      this.loggedIn=true;
    }
    else if(!this.succeeded)
    {
      this.notLoggedIn=true;
      this.loggedIn=false;
    }
    },2000)
    form.reset();
    
  }

  showText(show:boolean){
    if(this.succeeded)
    {
      this.notLoggedIn=false;
      this.loggedIn=true;
    }
    else if(!this.succeeded)
    {
      this.notLoggedIn=true;
      this.loggedIn=false;
    }
  }

  goToSignUp(){
    this.authService.goToRegister();
  }
}