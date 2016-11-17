import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {BlogService} from "../../service/blog.service";

@Component({
    moduleId: module.id,
    selector: 'blog-tags',
    templateUrl: 'tags.component.html',
    styleUrls: []
})
export class TagsComponent implements OnInit{

    tags: string[];

    errorMessage: string;

    constructor(
        private blogService: BlogService,
        private activatedRoute: ActivatedRoute,){}

    ngOnInit(): void {
        this.getTags();
    }

    getTags(){
        this.blogService.getTagList().subscribe(
            tags => {
                this.tags = tags;
            },
            error => this.errorMessage = <any>error
        );
    }

}