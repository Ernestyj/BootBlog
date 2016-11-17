import {Component, OnInit} from "@angular/core";

import {InfoService} from "../../service/info.service";
import {Info} from "../../model/info";

@Component({
    moduleId: module.id,
    selector: "blog-header",
    templateUrl: "header.component.html",
    styleUrls: []
})
export class HeaderComponent implements OnInit{

    info: Info;
    errorMessage: string;

    constructor(private infoService: InfoService){}

    get diagnostic(){
        return 'get info: '+JSON.stringify(this.info);
    }

    ngOnInit(): void {
        this.getInfo();
    }

    getInfo() {
        this.infoService.getInfo().subscribe(
            info => this.info = info,
            error =>  this.errorMessage = <any>error
        );
    }
}