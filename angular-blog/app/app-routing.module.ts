import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";

import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./service/auth-guard.service";
import {InfoService} from "./service/info.service";

@NgModule({
    imports: [RouterModule.forRoot(
        [
            { path: 'login', component: LoginComponent },
        ]
    )],
    exports: [RouterModule],
})
export class AppRoutingModule{

}

