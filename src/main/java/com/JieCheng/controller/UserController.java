package com.JieCheng.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhang on 2017/6/2.
 */
@RestController
public class UserController extends BaseController {
    /*
    修改密码
     */
    @RequestMapping("/modifypassword")
    public String modifyPassWord(HttpServletRequest httpServletRequest) {
        return userService.modifyPassWord(httpServletRequest);
    }

    /*
    重置密码
     */
    @RequestMapping("findPassWord")
    public String findPassWord(HttpServletResponse httpServletResponse, @RequestParam("IDCard") String IDCard, @RequestParam("newPassWord") String newPassWord) {
        return userService.findPassWord(httpServletResponse, IDCard, newPassWord);
    }

    /*
    修改用户在线状态为不在线
     */
    @RequestMapping("/changeOnline")
    public String changeOnline(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("IDCard") String IDCard, @RequestParam("status") String status) {
        return userService.changeOnline(httpServletRequest, httpServletResponse, IDCard, status);
    }

    /*
    随机做题
     */
    @RequestMapping("/randomexam")
    public String randomExam(HttpServletRequest httpServletRequest, @RequestParam("carexam") String carexam, @RequestParam("cartype") String cartype) {
        return subjectService.selectRandomSubject(httpServletRequest);
    }
}
