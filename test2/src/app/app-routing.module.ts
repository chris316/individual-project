import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';


import {LoginFormComponent} from './login-form/login-form.component';
import {RegisterFormComponent} from './register/register-form.component';
import { SearchHomeComponent } from './search/search-home.component';
import {FansComponent} from './fans/fans.component';
import {CompareComponent} from './compare/compare.component';
import {DetailsComponent} from './details/details.component';
import {ProjectsComponent} from './projects/projects.component';
import {AuthGuardService as AuthGuard} from './auth-guard.service';

const routes: Routes = [
  {path:'',pathMatch:'full',redirectTo:'login'},
  {path: 'login',component: LoginFormComponent},
  {path: 'register', component: RegisterFormComponent},
  {path: 'searchHome',component:SearchHomeComponent, canActivate:[AuthGuard]},
  {path: 'fans',component:FansComponent,canActivate:[AuthGuard]},
  {path: 'compare',component:CompareComponent,canActivate:[AuthGuard]},
  {path: 'details',component:DetailsComponent,canActivate:[AuthGuard]},
  {path:'project',component:ProjectsComponent,canActivate:[AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
