import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';
import {FansService} from '../fans-service';
import { FansData } from "../fans-data.model";
import { ProjectData } from "../project-data.model";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent{
    fanArray:Array<FansData>=[];
    projectsArray:Array<ProjectData>=[];
    projects:any;


    constructor(public authService:AuthService,public fansService:FansService){
        setTimeout(()=>{

            this.projectsArray=this.fansService.getProjects();
            this.fanArray=this.fansService.getProjectsFans();
            setTimeout(()=>{
                console.log(this.fanArray);
            },3000)
            },4000)

    }

    loadFanData(fan:FansData){
        this.fansService.loadDetails(fan);
      }


    goToFans(){
        this.authService.goToFans();
      }
    
      logout(){
        this.authService.logout();
      }
}