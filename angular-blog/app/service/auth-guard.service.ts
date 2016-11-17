import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivateChild} from "@angular/router";
import {Injectable} from "@angular/core";
import {InfoService} from "./info.service";

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild{

    constructor(
        private infoService: InfoService,
        private router: Router
    ) {}

    //ActivatedRouteSnapshot 包含了 即将 被激活的路由，RouterStateSnapshot 包含了该应用 即将 到达的状态
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let url: string = state.url;
        return this.checkLogin(url);
    }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        return this.canActivate(childRoute, state);
    }

    private checkLogin(url: string): boolean {
        if (this.infoService.isLoginSuccess) { return true; }
        // Store the attempted URL for redirecting
        this.infoService.redirectUrl = url;
        // Navigate to the login page with extras
        this.router.navigate(['/login']);
        return false;
    }

}