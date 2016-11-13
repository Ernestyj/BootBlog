package com.eugene.controller;

import com.eugene.model.Blog;
import com.eugene.model.Info;
import com.eugene.service.BlogService;
import com.eugene.service.InfoService;
import com.eugene.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }

//    @GetMapping("/")
//    public String welcome(Model model) {
//        model.addAttribute("info",infoService.getInfo());
//        return "index";
//    }

    @GetMapping("/archives/{page}")
    public String archives(@PathVariable int page,Model model){
        model.addAttribute("info",infoService.getInfo());
        model.addAttribute("archives",blogService.getArchive(page));
        model.addAttribute("pageNum",blogService.getArchiveNum());
        model.addAttribute("current",page);
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("info",infoService.getInfo());
        return "projects";
    }
    @GetMapping("/projects/{page}")
    public String projectPage(@PathVariable int page,Model model) {
        model.addAttribute("info",infoService.getInfo());
        model.addAttribute("projects",projectService.getPros(page));
        model.addAttribute("pageNum",projectService.getPageNum());
        model.addAttribute("current",page);
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        model.addAttribute("info",infoService.getInfo());
        model.addAttribute("tags",blogService.getTagList());
        return "tags";
    }
    @GetMapping("/tags/{tagName}")
    public String tagName(@PathVariable String tagName,Model model) {
        model.addAttribute("info",infoService.getInfo());
        List<Blog> views=blogService.getBlogByTag(tagName);
        model.addAttribute("views",views);
        model.addAttribute("tagName",tagName);
        return "tagView";
    }

    @GetMapping("/about")
    public String about(Model model, HttpServletResponse response) {
        model.addAttribute("info",infoService.getInfo());
        model.addAttribute("resume",infoService.getResumeView());
        return "about";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable int id,Model model) {
        model.addAttribute("info",infoService.getInfo());
        Blog blogView=blogService.getBlog(id);
        Blog prev=blogService.getPrevBlog(id);
        Blog next=blogService.getNextBlog(id);
        model.addAttribute("prev",prev);
        model.addAttribute("next",next);
        model.addAttribute("article",blogView.getArticle());
        return "post";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        String result = request.getParameter("result");
        if (result != null && result.equals("fail")) {
            model.addAttribute("success", 0);
        }
        return "login";
    }
    @PostMapping("/login.action")
    public String doLogin(Info user, HttpServletRequest request) {
        boolean result = infoService.login(user);
        if (result) {
            infoService.addSession(request, user);
            return "redirect:/admin";
        } else {
            return "redirect:/login?result=fail";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        infoService.destroySession(request);
        return "redirect:/login";
    }
}
