import {InfoService} from "../../service/info.service";
import {Component, OnInit} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'admin-resume',
    templateUrl: 'admin-resume.component.html',
    styleUrls: []
})
export class AdminResumeComponent implements OnInit {

    resumeMd: string;

    errorMessage: string;

    constructor(private infoService: InfoService) {
    }

    ngOnInit(): void {
        this.getResumeMd();
    }

    getResumeMd(){
        this.infoService.getResumeMd().subscribe(
            resume => this.resumeMd = resume,
            error => this.errorMessage = <any>error
        );
    }

    get diagnostic(){
        return this.resumeMd;
    }

}