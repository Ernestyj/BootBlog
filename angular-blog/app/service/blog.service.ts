import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {BaseService} from "./base.service";
import {Archive} from "../model/archive";
import {Blog} from "../model/blog";
import {Constants} from "../constants";

@Injectable()
export class BlogService extends BaseService{

    constructor(private http:Http) {
        super()
    }

    getArchives(page: number): Observable<Archive[]>{
        return this.http.get(Constants.BASE_URL+'/archives/'+page)
            .map(res => {
                console.log('getArchives: ', res.text());
                let archives: Archive[] = [];
                res.json().forEach( obj => archives.push(<Archive>obj) );
                return archives;
            })
            .catch(this.handleError)
    }

    getArchiveNum(): Observable<number>{
        return this.http.get(Constants.BASE_URL+'/archives/num')
            .map(res => {
                console.log('getArchiveNum: ', res.text());
                return parseInt(res.text());
            })
            .catch(this.handleError)
    }

    getTagList(): Observable<string[]>{
        return this.http.get(Constants.BASE_URL+'/tags')
            .map(res => {
                console.log('getTagList: ', res.text());
                let tags: string[] = [];
                res.json().forEach( obj => tags.push(<string>obj) );
                return tags;
            })
            .catch(this.handleError)
    }

    getTagBlogs(tagName: string): Observable<Blog[]>{
        return this.http.get(Constants.BASE_URL+'/tags/'+tagName)
            .map(res => {
                console.log('getTagBlogs: ', res.text());
                let blogs: Blog[] = [];
                res.json().forEach( obj => blogs.push(<Blog>obj) );
                return blogs;
            })
            .catch(this.handleError)
    }

    getBlog(vid: number): Observable<Blog>{
        return this.http.get(Constants.BASE_URL+'/post/'+vid)
            .map(res => {
                console.log('getBlog: ', res.text());
                return res.json();
            })
            .catch(this.handleError)
    }

    getPrevBlog(vid: number): Observable<Blog>{
        return this.http.get(Constants.BASE_URL+'/post/prev/'+vid)
            .map(res => {
                console.log('getPrevBlog: ', res.text());
                return res.json();
            })
            .catch(this.handleError)
    }

    getNextBlog(vid: number): Observable<Blog>{
        return this.http.get(Constants.BASE_URL+'/post/next/'+vid)
            .map(res => {
                console.log('getNextBlog: ', res.text());
                return res.json();
            })
            .catch(this.handleError)
    }

}