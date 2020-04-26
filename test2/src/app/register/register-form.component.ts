import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';


@Component({
  selector: 'app-register',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  user:User

  submitted = false;
  notRegistered=false;
  registered=false;
  succeeded:boolean;

  constructor(public authService:AuthService){
    this.user=new User();
    this.submitted=false;
    this.notRegistered=false;
    this.registered=false;
    this.succeeded=false;
    
  }

  onSubmit(form: NgForm) { 
    if(form.invalid)
    {
      return;
    }
    this.authService.register(this.user.username,this.user.password);
    setTimeout(()=>{
    this.succeeded=this.authService.showIfCorrect();
    },1500)
    setTimeout(()=>{
    if(this.succeeded)
    {
      this.notRegistered=false;
      this.registered=true;
    }
    else if(!this.succeeded)
    {
      this.notRegistered=true;
      this.registered=false;
    }
    },2000)
    form.reset();
  }

    goToLogin(){
    this.authService.goToLogin();
  }
}