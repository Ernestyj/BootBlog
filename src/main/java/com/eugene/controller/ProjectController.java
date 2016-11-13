package com.eugene.controller;

import com.eugene.model.Project;
import com.eugene.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DCLab on 2016/11/11.
 */
@RestController
@RequestMapping("/admin")
public class ProjectController {
    @Autowired
    private ProjectService projectSer;

    @GetMapping("/project/{page}")
    public String project(@PathVariable int page, Model model){
        model.addAttribute("current",page);
        model.addAttribute("pageNum",projectSer.adminGetPageNum());
        model.addAttribute("proList",projectSer.adminGetPros(page));
        return "admin/project";
    }

    @PostMapping("/addPro.action")
    public String addProject(Project project){
        projectSer.addPro(project);
        return "redirect:/admin/project/1";
    }

    @GetMapping("/deletePro/{id}")
    public String deletePro(@PathVariable int id){
        projectSer.deletePro(id);
        return "redirect:/admin/project/1";
    }

    @ResponseBody
    @GetMapping("/pro.json")
    public Project getProJson(HttpServletRequest request){
        String idStr=request.getParameter("id");
        return projectSer.getProById(idStr);
    }

    @PostMapping("/updPro.action")
    public String updatePro(Project project){
        projectSer.updatePro(project);
        return "redirect:/admin/project/1";
    }
}
