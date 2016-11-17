import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";

import {Project} from "../../model/project";
import {ProjectService} from "../../service/project.service";

@Component({
    moduleId: module.id,
    selector: 'blog-projects',
    templateUrl: 'projects.component.html',
    styleUrls: []
})
export class ProjectsComponent implements OnInit{

    projects: Project[];
    pageTotalNum: number;
    currentPage: number;

    errorMessage: string;

    constructor(
        private projectService: ProjectService,
        private activatedRoute: ActivatedRoute,){}

    ngOnInit(): void {
        this.activatedRoute.params.forEach((params: Params) => {
            let page = <number>params['page'];
            this.currentPage = page;
        })
        this.getProjects(this.currentPage);
        this.getProjectNum();
    }

    getProjects(page: number){
        this.projectService.getProjects(page).subscribe(
            projects => {
                this.projects = projects;
            },
            error => this.errorMessage = <any>error
        );
    }

    getProjectNum(){
        this.projectService.getProjectNum().subscribe(
            pageNum => this.pageTotalNum = pageNum,
            error => this.errorMessage = <any>error
        );
    }

    get diagnostic(){
        return 'get projects: '+JSON.stringify(this.projects)
            +'\n get pageTotalNum: '+this.pageTotalNum
            +'\n get currentPage: '+this.currentPage;
    }

}