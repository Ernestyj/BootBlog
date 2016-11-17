import {OnInit, Component} from "@angular/core";
import {BlogService} from "../../service/blog.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Blog} from "../../model/blog";

@Component({
    moduleId: module.id,
    selector: 'blog-post',
    templateUrl: 'post.component.html',
    styleUrls: []
})
export class PostComponent implements OnInit{

    blog: Blog;
    prevBlog: Blog;
    nextBlog: Blog;
    id: number;

    errorMessage: string;

    constructor(
        private blogService: BlogService,
        private activatedRoute: ActivatedRoute,){}

    ngOnInit(): void {
        this.activatedRoute.params.forEach((params: Params) => {
            let id = <number>params['id'];
            this.id = id;
        })
        this.getBlog(this.id);
        this.getPrevBlog(this.id);
        this.getNextBlog(this.id);
    }

    getBlog(id: number){
        this.blogService.getBlog(id).subscribe(
            blog => this.blog = blog,
            error => this.errorMessage = <any>error
        )
    }

    getPrevBlog(id: number){
        this.blogService.getPrevBlog(id).subscribe(
            blog => this.prevBlog = blog,
            error => this.errorMessage = <any>error
        )
    }

    getNextBlog(id: number){
        this.blogService.getNextBlog(id).subscribe(
            blog => this.nextBlog = blog,
            error => this.errorMessage = <any>error
        )
    }

    get diagnostic(){
        return 'get blog: '+JSON.stringify(this.blog)
    }

}