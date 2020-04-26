import { Injectable} from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Router } from "@angular/router";
import { Subject, Observable } from "rxjs";

import { AuthData } from "./auth-data.model";

const BACKEND_URL = "http://localhost:8080/individual_project_war_exploded" + "/users";

@Injectable({providedIn: "root"})
export class AuthService{
    private userId:number;
    private userName:string;
    private pass:string;
    public succeeded:boolean;
    myResponse:any;
    signUpResponse:any;
    authenticated=false;

    constructor(private http: HttpClient, private router:Router){
    }

    getUserId(){
        return this.userId;
    }

    isAuthenticated(){
        return this.authenticated;
    }

    getUsername(){
        return this.userName;
    }

    getPassword(){
        return this.pass;
    }

    login(entryUsername:string,entryPassword:string){
        let params=new HttpParams().set('username',entryUsername).set('password',entryPassword);
        this.succeeded=false;
        this.http.get(BACKEND_URL+"/login",{params})
        .subscribe(
            loginResponse=>{
                console.log("got a response");
                this.myResponse=loginResponse;
                if(this.myResponse.username!=null)
                {
                    console.log("user was found");
                this.userId=this.myResponse.id;
                this.userName=this.myResponse.username;
                this.pass=this.myResponse.password;
                this.succeeded=true;
                this.authenticated=true;
                setTimeout(() => {
                    this.router.navigate(['searchHome']);
                }, 1500);
                }
                else if(this.myResponse.username==null)
                {
                    console.log("user was not found");
                    this.succeeded=false;
                }
            },
            error=>{
                console.log("error connecting");
                this.succeeded=false;
            }
        );

    }

    showIfCorrect():boolean{
        if(this.succeeded)
        return true;
        else if(!this.succeeded)
        return false;
    }

    goToRegister(){
        this.router.navigate(['register']);

    }

    goToLogin(){
        this.router.navigate(['login']);
    }

    goToFans(){
        this.router.navigate(['fans']);
    }

    goToProjects(){
        this.router.navigate(['project']);
    }

    register(entryUsername:string,entryPassword:string){
        let params=new HttpParams().set('username',entryUsername).set('password',entryPassword);
        this.succeeded=false;
        this.http.post(BACKEND_URL+"/register?username="+entryUsername+"&password="+entryPassword,null)
        .subscribe(
            registerResponse=>{
                console.log("got a response");
                this.signUpResponse=registerResponse;
                if(this.signUpResponse.username!=null)
                {
                    console.log("user was registered");
                this.succeeded=true;
                this.router.navigate(['login']);
                }
                else if(this.signUpResponse.username==null)
                {
                    console.log("user was not found");
                    this.succeeded=false;
                }
            },
            error=>{
                console.log("error connecting");
                this.succeeded=false;
            }
        );
    }

    logout(){
        this.userId=0;
        this.userName=null;
        this.pass=null;
        this.authenticated=false;
        this.router.navigate(['']);
    }

}