package com.JieCheng.controller;

/**
 * Created by Zhang on 2017/5/8.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PageController extends BaseController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/studentCenter")
    public String studentcenter() {
        return "studentCenter";
    }

    @RequestMapping("/restartPassword")
    public String restartpassword() {
        return "restartPassword";
    }

    @RequestMapping("/userAdmin")
    public String useradmin() {
        return "userAdmin";
    }

    @RequestMapping("/topicAdmin")
    public String topicadmin() {
        return "topicAdmin";
    }

    @RequestMapping("/myError")
    public String myError() {
        return "myError";
    }

    @RequestMapping("/myCollect")
    public String myCollect() {
        return "myCollect";
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest httpServletRequest, Model model,
                        @RequestParam("loginName1") String loginName, @RequestParam("passWord1") String passWord) {
        return userService.checkUser(loginName, passWord, "admin", model, httpServletRequest);
    }


    @RequestMapping("/index")
    public String checkUser(HttpServletRequest httpServletRequest, Model model,
                            @RequestParam("loginName") String loginName, @RequestParam("passWord") String passWord) {
        return userService.checkUser(loginName, passWord, "index", model, httpServletRequest);
    }


    @RequestMapping("/center")
    public String center(HttpServletRequest httpServletRequest, Model model,
                         @RequestParam("examtype") String examtype, @RequestParam("carexam") String carexam, @RequestParam("cartype") String cartype) {
        return subjectService.centerPage(httpServletRequest, model, examtype, carexam, cartype);
    }
}
