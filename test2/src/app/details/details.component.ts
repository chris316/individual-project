import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';
import {FansService} from '../fans-service';
import { FansData } from "../fans-data.model";
import { ProjectData } from "../project-data.model";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent{
    fanDetails:FansData;
    projectsArray:Array<ProjectData>=[];
    projects:any;



    constructor(public authService:AuthService,public fansService:FansService){
        setTimeout(()=>{
          this.fanDetails=this.fansService.getDetails();
          this.projectsArray=this.fansService.getProjects();
          },3000)
      }

      back(){
        this.fansService.goToFans();
    }


    goToProject(){
        this.fansService.loadProjectFans();
        setTimeout(()=>{
          this.authService.goToProjects();
        },1500)
    }
  
    logout(){
      this.authService.logout();
    }

    goToLink(){
        console.log("clicked link");
    }

    addToProject(){
        this.fansService.addFanToProject(this.projects, this.fanDetails.id);
      }







}