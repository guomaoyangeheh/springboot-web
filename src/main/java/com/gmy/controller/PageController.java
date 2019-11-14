package com.gmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Guo Mao Yang
 * @date 2019/9/25 13:42
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/login-1")
    public String login(){
        return "page/login-1";
    }
}
