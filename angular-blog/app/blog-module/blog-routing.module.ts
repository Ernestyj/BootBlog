import {RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home/home.component";
import {ArchivesComponent} from "./archives/archives.component";
import {ProjectsComponent} from "./projects/projects.component";
import {TagsComponent} from "./tags/tags.component";
import {TagBlogsComponent} from "./tag-blogs/tag-blogs.component";
import {AboutComponent} from "./about/about.component";
import {PostComponent} from "./post/post.component";
import {BlogAppComponent} from "./blog-app.component";

@NgModule({
    imports: [RouterModule.forChild([
            { path: '', component: BlogAppComponent,
                children: [
                    { path: '', redirectTo: '/home', pathMatch:'full'},
                    { path: 'home', component: HomeComponent},
                    { path: 'archives/:page', component: ArchivesComponent },
                    { path: 'projects/:page', component: ProjectsComponent },
                    { path: 'tags', component: TagsComponent },
                    { path: 'tags/:tagName', component: TagBlogsComponent },
                    { path: 'about', component: AboutComponent },
                    { path: 'post/:id', component: PostComponent },
                ]
            },
        ])
    ],
    exports: [ RouterModule ]
})
export class BlogRoutingModule { }