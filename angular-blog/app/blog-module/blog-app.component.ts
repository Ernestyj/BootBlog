import {Component} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'blog-app',
    styleUrls: [],
    template: `
    <blog-header ></blog-header>
    <router-outlet></router-outlet>
    <blog-footer ></blog-footer>
    `
})
export class BlogAppComponent {

}