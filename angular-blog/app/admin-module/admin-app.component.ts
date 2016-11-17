import {Component, OnInit} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'admin-app',
    styleUrls: ['../../static/css/sb-admin.min.css',
        '../../static/css/editormd.min.css',
        '../../static/css/bootstrap-tagsinput.min.css',
        '//cdn.bootcss.com/morris.js/0.4.3/morris.css'],
    template: `
    <admin-header></admin-header>
    <router-outlet></router-outlet>
    `
})
export class AdminAppComponent implements OnInit{

    constructor() {}

    ngOnInit(): void {
    }
}