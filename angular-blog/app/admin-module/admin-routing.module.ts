import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {AdminAppComponent} from "./admin-app.component";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {AdminInfoComponent} from "./admin-info/admin-info.component";
import {AdminResumeComponent} from "./admin-resume/admin-resume.component";
import {AuthGuard} from "../service/auth-guard.service";

@NgModule({
    imports: [RouterModule.forChild([
        { path: 'admin', component: AdminAppComponent, canActivate: [AuthGuard],
            children: [
                { path: '', canActivateChild: [AuthGuard], //无组件路由 : 不借助组件对路由进行分组
                    children: [
                        { path: '', component: AdminHomeComponent},
                        { path: 'info', component: AdminInfoComponent },
                        { path: 'resume', component: AdminResumeComponent },
                    ]
                },

            ]
        }
    ])],
    exports: [ RouterModule ],
})
export class AdminRoutingModule { }