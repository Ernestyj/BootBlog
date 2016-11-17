import {Headers} from "@angular/http";

export class Constants{
    static BASE_URL: string = "http://localhost:8080";
    static HEADERS_JSON = new Headers({'Content-Type': 'application/json'});
    static ADMIN_BASE_URL: string = Constants.BASE_URL+'/admin';
}