import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";

import {AdminAppComponent} from "./admin-app.component";
import {AdminHeaderComponent} from "./admin-header/admin-header.component";
import {AdminRoutingModule} from "./admin-routing.module";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {MonitorService} from "../service/monitor.service";
import {AdminInfoComponent} from "./admin-info/admin-info.component";
import {InfoService} from "../service/info.service";
import {AdminResumeComponent} from "./admin-resume/admin-resume.component";

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, AdminRoutingModule],
    declarations: [AdminAppComponent, AdminHeaderComponent, AdminHomeComponent, AdminInfoComponent,
        AdminResumeComponent],
    providers: [MonitorService, InfoService],
    bootstrap: [AdminAppComponent]
})
export class AdminModule{

}