import {OnInit, Component} from "@angular/core";
import {Info} from "../../model/info";
import {InfoService} from "../../service/info.service";

@Component({
    moduleId: module.id,
    selector: "admin-info",
    templateUrl: "admin-info.component.html",
    styleUrls: []
})
export class AdminInfoComponent implements OnInit {

    info: Info;

    errorMessage: string;

    constructor(private infoService: InfoService){}

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