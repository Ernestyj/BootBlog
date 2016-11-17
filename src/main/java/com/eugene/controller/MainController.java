package com.eugene.controller;

import com.eugene.model.Archive;
import com.eugene.model.Blog;
import com.eugene.model.Info;
import com.eugene.model.Project;
import com.eugene.service.BlogService;
import com.eugene.service.InfoService;
import com.eugene.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by DCLab on 2016/11/7.
 */
@Controller
public class MainController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

    @GetMapping("/info")
    public @ResponseBody Info getInfo() {
        return infoService.getInfo();
    }

    @GetMapping("/archives/{page}")
    public @ResponseBody List<Archive> getArchives(@PathVariable int page){
        return blogService.getArchive(page);
    }
    @GetMapping("/archives/num")
    public @ResponseBody int getArchiveNum(){
        return blogService.getArchiveNum();
    }

    @GetMapping("/projects/{page}")
    public @ResponseBody List<Project> getProjects(@PathVariable int page){
        return projectService.getPros(page);
    }
    @GetMapping("/projects/num")
    public @ResponseBody int getProjectNum(){
        return projectService.getPageNum();
    }

    @GetMapping("/tags")
    public @ResponseBody List<String> getTags() {
        return blogService.getTagList();
    }
    @GetMapping("/tags/{tagName}")
    public @ResponseBody List<Blog> getTagBlogs(@PathVariable String tagName){
        return blogService.getBlogByTag(tagName);
    }

    @GetMapping("/about")
    public @ResponseBody String getResumeView() {
        return infoService.getResumeView();
    }

    @GetMapping("/post/{id}")
    public @ResponseBody Blog getBlog(@PathVariable int id) {
        return blogService.getBlog(id);
    }
    @GetMapping("/post/prev/{id}")
    public @ResponseBody Blog getPrevBlog(@PathVariable int id) {
        return blogService.getPrevBlog(id);
    }
    @GetMapping("/post/next/{id}")
    public @ResponseBody Blog getNextBlog(@PathVariable int id) {
        return blogService.getNextBlog(id);
    }

    @PostMapping("/login.action")
    public @ResponseBody boolean doLogin(@RequestBody Info user, HttpServletRequest request) {
        boolean result = infoService.login(user);
        if (result) {
            infoService.addSession(request, user);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/logout")
    public @ResponseBody void logout(HttpServletRequest request) {
        infoService.destroySession(request);
    }
}
