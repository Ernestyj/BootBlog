import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {NgModule} from "@angular/core";

import {BlogAppComponent} from "./blog-app.component";
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {HomeComponent} from "./home/home.component";
import {ArchivesComponent} from "./archives/archives.component";
import {ProjectsComponent} from "./projects/projects.component";
import {TagsComponent} from "./tags/tags.component";
import {TagBlogsComponent} from "./tag-blogs/tag-blogs.component";
import {AboutComponent} from "./about/about.component";
import {PostComponent} from "./post/post.component";
import {InfoService} from "../service/info.service";
import {BlogService} from "../service/blog.service";
import {ProjectService} from "../service/project.service";
import {BlogRoutingModule} from "./blog-routing.module";


@NgModule({
    imports:      [ BrowserModule, HttpModule, FormsModule, BlogRoutingModule ],
    declarations: [ BlogAppComponent, HeaderComponent, FooterComponent, HomeComponent,
        ArchivesComponent, ProjectsComponent, TagsComponent, TagBlogsComponent,
        AboutComponent, PostComponent ],
    bootstrap:    [ BlogAppComponent ],
    providers: [InfoService, BlogService, ProjectService]
})
export class BlogModule {

}