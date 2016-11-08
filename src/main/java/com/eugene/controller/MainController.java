package com.eugene.controller;

import com.eugene.service.BlogService;
import com.eugene.service.InfoService;
import com.eugene.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String home(Model model) {
        model.addAttribute("info", infoService.getInfo());
        return "index";
    }

}
