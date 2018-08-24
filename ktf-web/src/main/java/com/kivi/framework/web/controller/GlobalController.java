package com.kivi.framework.web.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局的控制器
 *
 */
@Controller
@RequestMapping( "/global" )
public class GlobalController {

    /**
     * 跳转到404页面
     *
     */
    @RequestMapping( path = "/error" )
    public String errorPage() {
        return "/404.html";
    }

    /**
     * 跳转到session超时页面
     *
     */
    @RequestMapping( path = "/sessionError" )
    public String errorPageInfo( Model model ) {
        model.addAttribute("tips", "session超时");
        return "/login.html";
    }

    @RequestMapping( path = "/baseUrl" )
    @ResponseBody
    public String baseUrl( HttpServletRequest request ) {
        StringBuffer baseUrl = new StringBuffer();
        try {
            URL url = new URL(request.getRequestURL().toString());
            baseUrl.append("http://").append(url.getHost()).append(":").append(url.getPort());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return baseUrl.toString();
    }
}
