import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import {BlogService} from "../../service/blog.service";
import {Blog} from "../../model/blog";

@Component({
    moduleId: module.id,
    selector: 'blog-archives',
    templateUrl: 'tag-blogs.component.html',
    styleUrls: []
})
export class TagBlogsComponent implements OnInit{

    blogs: Blog[];
    tagName: string;

    errorMessage: string;

    constructor(
        private blogService: BlogService,
        private activatedRoute: ActivatedRoute,){}

    ngOnInit(): void {
        this.activatedRoute.params.forEach((params: Params) => {
            let tagName = params['tagName'];
            this.tagName = tagName;
        })
        this.getTagBlogs(this.tagName);
    }

    getTagBlogs(tagName: string){
        this.blogService.getTagBlogs(tagName).subscribe(
            blogs => this.blogs = blogs,
            error => this.errorMessage = <any>error
        );
    }

}