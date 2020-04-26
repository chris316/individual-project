import { Injectable} from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Router } from "@angular/router";
import { Subject, Observable } from "rxjs";

import { FansData } from "./fans-data.model";
import {ProjectData} from "./project-data.model";

const BACKEND_URL = "http://localhost:8080/individual_project_war_exploded" + "/fans";
const PROJECTS_URL="http://localhost:8080/individual_project_war_exploded" + "/projects";

@Injectable({providedIn: "root"})
export class FansService{
    fansArray:Array<FansData>=[];
    compareArray:Array<FansData>=[];
    projectsArray:Array<ProjectData>=[];
    projectFans:Array<FansData>=[];
    fanData:FansData;
    fansResponse:any;
    projectsResponse:any;


    constructor(private http: HttpClient, private router:Router){
        console.log("entered constructor for fans service");
        this.http.get<Array<FansData>>(BACKEND_URL+"/list")
        .subscribe(
            fanListResponse=>{
                console.log("got a response");
                if(fanListResponse!=null)
                {
                    console.log("fans were found");
                this.fansResponse=fanListResponse;
                for(let fan of fanListResponse)
                {
                    this.fansArray.push(fan);
                }
                }
                else if(fanListResponse==null)
                {
                    console.log("fans were not found");
                }
            },
            error=>{
                console.log("error connecting");
            }
        );
        setTimeout(()=>{
            this.loadProjects();
            setTimeout(()=>{
                this.loadProjectFans();
                },3000)
            },3000)
    }

    getFans(){
        return this.fansArray;
    }

    goToFans(){
        this.router.navigate(['fans']);
    }

    loadDetails(fan:FansData){
        this.fanData=fan;
        this.router.navigate(['details']);
    }

    getProjects(){
        return this.projectsArray;
    }

    loadProjects(){
    this.http.get<Array<ProjectData>>(PROJECTS_URL+"/readProjects")
    .subscribe(
        projectListResponse=>{
            console.log("got a response for projects");
            if(projectListResponse!=null)
            {
                console.log("projects were found");
                this.projectsResponse=projectListResponse;
                for(let project of projectListResponse)
                {
                    this.projectsArray.push(project);
                }
            }
            else if(projectListResponse==null)
            {
                console.log("no projects found");
            }
        },
        error=>{
            console.log("error connecting");
        }
    );
    }
    getProjectsFans(){
            return this.projectFans;

    }

    loadProjectFans(){
        setTimeout(()=>{
            console.log("loading fans in projects");
            this.projectFans=[];
            let params=new HttpParams().set('projectId',this.projectsArray[0].id);
            this.http.get<Array<FansData>>(PROJECTS_URL+"/getProjectFans",{params})
            .subscribe(
                projectFansResponse=>{
                    console.log("got a fans project response");
                    if(projectFansResponse!=null)
                    {
                        console.log("project fans were found");
                        for(let fan of projectFansResponse)
                        {
                            this.projectFans.push(fan);
                        }
                    }
                    else if(projectFansResponse==null)
                    {
                        console.log("no projects found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
            },3000)
    }

    getDetails(){
        return this.fanData;
    }

    addFanToProject(projectId:string,fanId:string)
    {
        console.log("adding fan:"+fanId+"to project: "+projectId);
        let params=new HttpParams().set('projectId',projectId).set('fanId',fanId);
        console.log("searching: "+params.toString());
        this.http.post(PROJECTS_URL+"/addFanToProject?projectId="+projectId+"&fanId="+fanId,null)
        .subscribe(
            addingResponse=>{
                console.log("got an adding response");
                if(addingResponse)
                {
                    console.log("fan added to project");
                }
                else if(!addingResponse)
                {
                    console.log("fan was not added");
                }
            },
            error=>{
                console.log("error connecting");
            }
        )

    }

    resetSearch(){
        this.fansArray=[];
        console.log("fans array: "+this.fansArray);
        this.http.get<Array<FansData>>(BACKEND_URL+"/list")
        .subscribe(
            fanListResponse=>{
                console.log("got a response");
                if(fanListResponse!=null)
                {
                    console.log("fans were found");
                this.fansResponse=fanListResponse;
                for(let fan of fanListResponse)
                {
                    this.fansArray.push(fan);
                }
                }
                else if(fanListResponse==null)
                {
                    console.log("fans were not found");
                }
            },
            error=>{
                console.log("error connecting");
            }
        );
    }

    searchAccessory(accessory:string)
    {
        console.log("finding some fans");
        let params=new HttpParams().set('accessories',accessory);
        console.log("searching: "+params.toString());
        this.fansArray=[];
        this.http.get<Array<FansData>>(BACKEND_URL+"/findAccessories",{params})
        .subscribe(
            fanListResponse=>{
                console.log("got a response");
                if(fanListResponse!=null)
                {
                    console.log("fans were found");
                this.fansResponse=fanListResponse;
                for(let fan of fanListResponse)
                {
                    this.fansArray.push(fan);
                }
                }
                else if(fanListResponse==null)
                {
                    console.log("fans were not found");
                }
            },
            error=>{
                console.log("error connecting");
            }
        );
    }

    searchModelYear(yearMin:string,yearMax:string)
    {
        console.log("finding some fans");
        if(!yearMin)
        {
            let params=new HttpParams().set('yearMax',yearMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findYears",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else if(!yearMax)
        {
            let params=new HttpParams().set('yearMin',yearMin);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findYears",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else{
            let params=new HttpParams().set('yearMin',yearMin).set('yearMax',yearMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findYears",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
    }

    
    searchAirflow(airMin:string,airMax:string)
    {
        console.log("finding some fans");
        if(!airMin)
        {
            let params=new HttpParams().set('airflowMax',airMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findAirflow",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else if(!airMax)
        {
            let params=new HttpParams().set('airflowMin',airMin);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findAirflow",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else{
            let params=new HttpParams().set('airflowMin',airMin).set('airflowMax',airMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findAirflow",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
    }

    
    searchPower(powMin:string,powMax:string)
    {
        console.log("finding some fans");
        if(!powMin)
        {
            let params=new HttpParams().set('powerMax',powMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findPower",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else if(!powMax)
        {
            let params=new HttpParams().set('powerMin',powMin);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findPower",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else{
            let params=new HttpParams().set('powerMin',powMin).set('powerMax',powMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findPower",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
    }

    searchSound(sounMin:string,sounMax:string)
    {
        console.log("finding some fans");
            let params=new HttpParams().set('soundMin',sounMin).set('soundMax',sounMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findSound",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
    }

    searchSpeed(speeMin:string,speeMax:string)
    {
        console.log("finding some fans");
        if(!speeMin)
        {
            let params=new HttpParams().set('speedMax',speeMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findFanSpeed",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else if(!speeMax)
        {
            let params=new HttpParams().set('speedMin',speeMin);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findFanSpeed",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
        else{
            let params=new HttpParams().set('speedMin',speeMin).set('speedMax',speeMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findFanSpeed",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
        }
    }

    searchSweep(sweeMin:string,sweeMax:string)
    {
        console.log("finding some fans");
            let params=new HttpParams().set('diameterMin',sweeMin).set('diameterMax',sweeMax);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findDiameter",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
    }

    searchManufacturer(man:string)
    {
        console.log("finding some fans");
            let params=new HttpParams().set('manufacturer',man);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findManufacturers",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
    }

    searchSeries(ser:string)
    {
        console.log("finding some fans");
            let params=new HttpParams().set('series',ser);
            console.log("searching: "+params.toString());
            this.fansArray=[];
            this.http.get<Array<FansData>>(BACKEND_URL+"/findSeries",{params})
            .subscribe(
                fanListResponse=>{
                    console.log("got a response");
                    if(fanListResponse!=null)
                    {
                        console.log("fans were found");
                    this.fansResponse=fanListResponse;
                    for(let fan of fanListResponse)
                    {
                        this.fansArray.push(fan);
                    }
                    }
                    else if(fanListResponse==null)
                    {
                        console.log("fans were not found");
                    }
                },
                error=>{
                    console.log("error connecting");
                }
            );
    }


    compareFans(fans:Array<FansData>)
    {
        this.compareArray=fans;
        this.router.navigate(['compare']);
    }

    getCompare(){
        return this.compareArray;
    }


}