import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';
import {FansService} from '../fans-service';
import { FansData } from "../fans-data.model";
import { ProjectData } from "../project-data.model";


@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styleUrls: ['./compare.component.css']
})
export class CompareComponent{
    compareArray:Array<FansData>=[];
    projectsArray:Array<ProjectData>=[];
    projects:any;

    constructor(public authService:AuthService,public fansService:FansService){
        setTimeout(()=>{
          this.compareArray=this.fansService.getCompare();
          this.projectsArray=this.fansService.getProjects();
          },4000)
      }

      back(){
          this.fansService.goToFans();
      }


      goToProjects(){
        this.fansService.loadProjectFans();
        setTimeout(()=>{
          this.authService.goToProjects();
        },1500)
      }
    
      logout(){
        this.authService.logout();
      }

      addToProject(fan:FansData){
        this.fansService.addFanToProject(this.projects, fan.id);
      }




}