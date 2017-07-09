package com.JieCheng.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Zhang on 2017/6/2.
 */
@RequestMapping("/user")
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
    @RequestMapping("/findPassWord")
    public String findPassWord(@RequestParam("IDCard") String IDCard,
                               @RequestParam("newPassWord") String newPassWord) {
        return userService.findPassWord(IDCard, newPassWord);
    }

    /*
    修改用户在线状态为不在线
     */
    @RequestMapping("/changeOnline")
    public String changeOnline(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                               @RequestParam("IDCard") String IDCard, @RequestParam("status") String status) {
        return userService.changeOnline(httpServletRequest, httpServletResponse, IDCard, status);
    }

    /*
    随机做题
     */
    @RequestMapping("/randomexam")
    public Map<String, Object> randomExam(HttpServletRequest httpServletRequest,
                                          @RequestParam("carExam") String carExam, @RequestParam("carType") String carType) {
        return subjectService.selectRandomSubject(httpServletRequest, carExam, carType);
    }

    /*
    新增用户照片
     */
    @PostMapping("/addUserImage")
    public String addUserImage(@RequestParam("file") MultipartFile file, @RequestParam("fileType") String fileType,
                               @RequestParam("fileSize") String fileSize, @RequestParam("userId") Integer userId) {
        return userService.addUserImage(file, fileType, fileSize, userId);
    }

    /*
    获取用户照片
     */
    @GetMapping("/getUserImage")
    public String getUserImage(HttpServletRequest httpServletRequest) {
        return userService.getUserImage(httpServletRequest);
    }
}
