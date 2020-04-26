import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';
import {FansService} from '../fans-service';
import { FansData } from "../fans-data.model";
import { ProjectData } from "../project-data.model";

@Component({
  selector: 'app-fans',
  templateUrl: './fans.component.html',
  styleUrls: ['./fans.component.css']
})
export class FansComponent{
  fanArray:Array<FansData>=[];
  compareArray:Array<FansData>=[];
  accessory:string;
  modelYearMin:string;
  modelYearMax:string;
  airflowMin:string;
  airflowMax:string;
  powerMin:string;
  powerMax:string;
  soundMin:string;
  soundMax:string;
  sweepMin:string;
  sweepMax:string;
  speedMin:string;
  speedMax:string;
  manufacturer:string;
  series:string;
  projectsArray:Array<ProjectData>=[];
  projects:any;

  constructor(public authService:AuthService,public fansService:FansService){
    setTimeout(()=>{
      this.fanArray=this.fansService.getFans();
      this.projectsArray=this.fansService.getProjects();
      },6000)
  }
    

  findFans(){
    this.fanArray=this.fansService.getFans();
  }

  addToProject(fan:FansData){
    this.fansService.addFanToProject(this.projects, fan.id);
  }

  onSubmit(form:NgForm)
  {    
    this.accessory=form.controls['accessory'].value;
    if(this.accessory)
    {
      this.fansService.searchAccessory(this.accessory);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000)
    }
    else if(this.modelYearMin||this.modelYearMax)
    {
      this.fansService.searchModelYear(this.modelYearMin,this.modelYearMax);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000)   
         this.modelYearMax='';
    this.modelYearMin='';
    }
   else if(this.airflowMin||this.airflowMax)
    {
      this.fansService.searchAirflow(this.airflowMin,this.airflowMax);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
           this.airflowMax='';
    this.airflowMax='';
    }
    else if(this.powerMin||this.powerMax)
    {
      this.fansService.searchPower(this.powerMin,this.powerMax);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
           this.powerMax='';
    this.powerMin='';
    }
    else if(this.soundMin||this.soundMax)
    {
      this.fansService.searchSound(this.soundMin,this.soundMax);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
           this.soundMax='';
    this.soundMin='';
    }
    else if(this.speedMin||this.speedMax)
    {
      this.fansService.searchSpeed(this.speedMin,this.speedMax);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
           this.speedMax='';
    this.speedMin='';
    }
    else if(this.sweepMin||this.sweepMax)
    {
      this.fansService.searchSweep(this.sweepMin,this.sweepMax);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
           this.sweepMax='';
    this.sweepMin='';
    }
    else if(this.manufacturer)
    {
      this.fansService.searchManufacturer(this.manufacturer);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
        this.manufacturer='';
    }
    else if(this.series)
    {
      this.fansService.searchSeries(this.series);
      setTimeout(()=>{
        this.fanArray=this.fansService.getFans();
        },2000) 
        this.series='';
    }
    form.reset();
  }

  clearSearch(form:NgForm){
    this.fansService.resetSearch();
    setTimeout(()=>{
      this.fanArray=this.fansService.getFans();
      },3000)
    form.reset();
  }

  loadFanData(fan:FansData){
    this.fansService.loadDetails(fan);
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

  onCompare(form: NgForm){
    for(let fan of this.fanArray)
    {
      if(fan.checked)
      {
        this.compareArray.push(fan);
      }
    }
    this.fansService.compareFans(this.compareArray);
      setTimeout(()=>{  
        for(let fan of this.fanArray)
      {
       fan.checked=false;
      }
      this.compareArray=[];
        },6000)
    form.reset();
  }
}