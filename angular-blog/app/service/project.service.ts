import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {BaseService} from "./base.service";
import {Observable} from "rxjs";
import {Project} from "../model/project";
import {Constants} from "../constants";

@Injectable()
export class ProjectService extends BaseService{

    constructor(private http:Http) {
        super()
    }

    getProjects(page: number): Observable<Project[]>{
        return this.http.get(Constants.BASE_URL+'/projects/'+page)
            .map(res => {
                console.log('getProjects: ', res.text());
                let projects: Project[] = [];
                res.json().forEach( obj => projects.push(<Project>obj) );
                return projects;
            })
            .catch(this.handleError)
    }

    getProjectNum(): Observable<number>{
        return this.http.get(Constants.BASE_URL+'/projects/num')
            .map(res => {
                console.log('getProjectNum: ', res.text());
                return parseInt(res.text());
            })
            .catch(this.handleError)
    }

}