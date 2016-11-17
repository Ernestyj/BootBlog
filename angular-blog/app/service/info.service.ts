import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Info} from "../model/info";
import {BaseService} from "./base.service";
import {Constants} from "../constants";

@Injectable()
export class InfoService extends BaseService{

    isLoginSuccess: boolean = false;
    redirectUrl: string;

    constructor(private http:Http) {
        super()
    }

    getInfo(): Observable<Info>{
        return this.http.get(Constants.BASE_URL+"/info")
            .map(res => {
                console.log('getInfo: ', res.text());
                return res.json();
            })
            .catch(this.handleError);
    }

    getResume(): Observable<string>{
        return this.http.get(Constants.BASE_URL+"/about")
            .map(res => {
                console.log('getResume: ', res.text());
                return res.text();
            })
            .catch(this.handleError);
    }

    getResumeMd(): Observable<string>{
        return this.http.get(Constants.ADMIN_BASE_URL+"/resume")
            .map(res => {
                console.log('getResumeMd: ', res.text());
                return res.text();
            })
            .catch(this.handleError);
    }

    doLogin(info: Info): Observable<boolean>{
        return this.http.post(Constants.BASE_URL+'/login.action',
                JSON.stringify(info), {headers: Constants.HEADERS_JSON})
            .map( res => {
                console.log('doLogin: ', res.text());
                if (res.text()=='true') this.isLoginSuccess = true;
                else this.isLoginSuccess =false;
                return res.text();
            })
            .catch(this.handleError);
    }

    logout(){
        return this.http.get(Constants.BASE_URL+"/logout")
            .map(res => {
                console.log('logout: ', res.status);
                this.isLoginSuccess = false;
            })
            .catch(this.handleError);
    }

}