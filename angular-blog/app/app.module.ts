import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";

import "./rxjs-extensions";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {InfoService} from "./service/info.service";
import {BlogService} from "./service/blog.service";
import {ProjectService} from "./service/project.service";
import {LoginComponent} from "./login/login.component";
import {AdminModule} from "./admin-module/admin.module";
import {BlogModule} from "./blog-module/blog.module";
import {AuthGuard} from "./service/auth-guard.service";

@NgModule({
    imports:      [ BrowserModule, HttpModule, FormsModule, AppRoutingModule,
        BlogModule, AdminModule],
    declarations: [ AppComponent, LoginComponent],
    bootstrap:    [ AppComponent ],
    providers: [InfoService, BlogService, ProjectService, AuthGuard]
})
export class AppModule {

}
