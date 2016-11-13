package com.eugene.controller;

import com.eugene.model.Info;
import com.eugene.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DCLab on 2016/11/11.
 */
@RestController
@RequestMapping("/admin")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/info")
    public String info(Model model) throws Exception {
        Info info = infoService.getInfo();
        model.addAttribute("info", info);
        return "admin/info";
    }


    @PostMapping("/info.action")
    public String updateInfo(Info info, Model model) {
        boolean result = infoService.updateInfo(info);
        model.addAttribute("targetUrl", "/admin/info");
        if (result) {
            model.addAttribute("result", 1);
            return "admin/result";
        } else {
            model.addAttribute("result", 0);
            return "admin/result";
        }
    }

    @PostMapping("/pass.action")
    public String passModify(String old_pass, String new_pass, HttpServletRequest request) {
        int result = infoService.modifyPw(old_pass, new_pass);
        if (result == 0) {
            infoService.destroySession(request);
        }
        return "redirect:/admin/info?result=" + result;
    }

    @GetMapping("/resume")
    public String resume(Model model) {
        model.addAttribute("md", infoService.getResumeMd());
        return "admin/resume";
    }
    @PostMapping("/resume.action")
    public String resumeUpdate(Info info, Model model) {
        infoService.updateResume(info);
        return "redirect:/admin/resume";
    }
}
