package com.yxm.oder.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author yxm
 * @date 2019/5/25 16:09:09
 */
@RestController
@RequestMapping("/env")
@RequestScope
public class EnvController {

    @Value("${env}")
    private String env;

    @RequestMapping("/dev")
    public String dev() {
        return env;
    }
}