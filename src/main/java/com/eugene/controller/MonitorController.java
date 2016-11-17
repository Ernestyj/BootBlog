package com.eugene.controller;

import com.eugene.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DCLab on 2016/11/11.
 */
@RestController
public class MonitorController {
    @Autowired
    private MonitorService moniterService;

    @GetMapping("/admin/monitor")
    public @ResponseBody int getMonitor() throws Exception {
        return moniterService.getFreeMemery();
    }
}
