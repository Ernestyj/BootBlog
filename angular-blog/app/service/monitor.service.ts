import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";

import {BaseService} from "./base.service";
import {Constants} from "../constants";

@Injectable()
export class MonitorService extends BaseService {

    constructor(private http: Http) {
        super()
    }

    getFreeMemery(): Observable<number>{
        return this.http.get(Constants.ADMIN_BASE_URL+'/monitor')
            .map(res => {
                console.log('getFreeMemery: ', res.text());
                return parseInt(res.text());
            })
            .catch(this.handleError);
    }

}