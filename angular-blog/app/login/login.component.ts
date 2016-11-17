import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

import {Info} from "../model/info";
import {InfoService} from "../service/info.service";

@Component({
    moduleId: module.id,
    selector: 'blog-login',
    templateUrl: 'login.component.html',
    styleUrls: ['../../../static/css/login-page.min.css', 'login.component.css']
})
export class LoginComponent implements OnInit{

    username: string;
    password: string;

    errorMessage: string;

    constructor(
        private infoService: InfoService,
        private router: Router
    ){}

    ngOnInit(): void {
    }

    onSubmit() {
        this.doLogin();
    }

    doLogin(){
        this.infoService.doLogin(new Info(this.username, this.password)).subscribe(
            res => {
                if(this.infoService.isLoginSuccess){
                    let redirect = this.infoService.redirectUrl ?
                        this.infoService.redirectUrl : '/admin';
                    this.router.navigate([redirect]);
                }
            },
            error =>  this.errorMessage = <any>error
        );
    }

    get diagnotic(){
        return 'username:'+this.username+', password:'+this.password;
    }

}