import {Component, OnInit} from "@angular/core";

import {InfoService} from "../../service/info.service";

@Component({
    moduleId: module.id,
    selector: 'blog-about',
    templateUrl: 'about.component.html',
    styleUrls: []
})
export class AboutComponent implements OnInit{

    resumeHtml: string;

    errorMessage: string;

    constructor(private infoService: InfoService){}

    ngOnInit(): void {
        this.getResume();
    }

    getResume(){
        this.infoService.getResume().subscribe(
            resume => this.resumeHtml = resume,
            error => this.errorMessage = <any>error
        );
    }

    get diagnostic(){
        return this.resumeHtml;
    }

}