import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";

import {Archive} from "../../model/archive";
import {BlogService} from "../../service/blog.service";

@Component({
    moduleId: module.id,
    selector: 'blog-archives',
    templateUrl: 'archives.component.html',
    styleUrls: []
})
export class ArchivesComponent implements OnInit{

    archives: Archive[];
    pageTotalNum: number;
    currentPage: number;

    errorMessage: string;

    constructor(
        private blogService: BlogService,
        private activatedRoute: ActivatedRoute,){}

    ngOnInit(): void {
        this.activatedRoute.params.forEach((params: Params) => {
            let page = <number>params['page'];
            this.currentPage = page;
        })
        this.getArchiveNum();
        this.getArchives(this.currentPage);
    }

    getArchives(page: number){
        this.blogService.getArchives(page).subscribe(
            archives => {
                this.archives = archives;
            },
            error => this.errorMessage = <any>error
        );
    }

    getArchiveNum(){
        this.blogService.getArchiveNum().subscribe(
            pageNum => this.pageTotalNum = pageNum,
            error => this.errorMessage = <any>error
        );
    }

    get diagnostic(){
        return 'get archives: '+JSON.stringify(this.archives)
            +'\n get pageTotalNum: '+this.pageTotalNum
            +'\n get currentPage: '+this.currentPage;
    }

}