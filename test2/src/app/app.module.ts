
import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent }  from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import {RegisterFormComponent} from './register/register-form.component';
import {SearchHomeComponent} from './search/search-home.component';
import { AppRoutingModule } from "./app-routing.module";
import {FansComponent} from './fans/fans.component';
import {CompareComponent} from './compare/compare.component';
import {DetailsComponent} from './details/details.component';
import {ProjectsComponent} from './projects/projects.component';
import { AuthGuardService } from './auth-guard.service';
import {AuthService} from './auth-service';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    SearchHomeComponent,
    FansComponent,
    CompareComponent,
    DetailsComponent,
    ProjectsComponent
  ],
  providers: [
    AuthService,
    AuthGuardService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
