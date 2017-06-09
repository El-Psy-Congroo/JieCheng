package com.JieCheng.controller;

import jxl.read.biff.BiffException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/10.
 */
@RestController
public class ServiceController extends BaseController {
    /*
    导出模板
     */
    @RequestMapping("/exporttemplet")
    public String exportTemplet(HttpServletRequest httpServletRequest, Model model, HttpServletResponse httpServletResponse) throws Exception {
        String type = httpServletRequest.getParameter("type");
        if (type.equals("subject")) {
            return subjectService.exportSubjectTemplet(httpServletRequest, httpServletResponse);
        } else if (type.equals("user")) {
            return userService.exportUserTemplet(httpServletRequest, httpServletResponse);
        }
        return "导出失败";
    }

    /*
    导入模板
     */
    @RequestMapping("/importtemplet")
    public String importTemplet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                @RequestParam("file") MultipartFile file) throws IOException, BiffException {
        String type = httpServletRequest.getParameter("type");
        if (type.equals("subject")) {
            return subjectService.importTemplet(file, httpServletRequest, httpServletResponse);
        } else {
            return userService.importTemplet(file, httpServletRequest, httpServletResponse);
        }
    }

    /*
    获取当前人员信息列表
     */
    @RequestMapping("/getAllUserInfo")
    public Map<String, Object> getAllUserInfo(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return userService.getAllUserInfo(page, limit);
    }

    /*
    获取当前题目信息列表
     */
    @RequestMapping("/getAllSubjectInfo")
    public Map<String, Object> getAllSubjectInfo(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return subjectService.getAllSubjectInfo(page, limit);
    }

    /*
    添加收藏
     */
    @RequestMapping("/addcollectsubject")
    public String addCollectSubject(HttpServletRequest httpServletRequest) {
        return subjectService.addCollectSubject(httpServletRequest);
    }

    /*
    删除收藏
     */
    @RequestMapping("/deletecollectsubject")
    public String deleteCollectSubject(HttpServletRequest httpServletRequest, Model model) {
        return subjectService.deleteCollectSubject(httpServletRequest);
    }

    /*
    删除错题
     */
    @RequestMapping("/deleteerrorsubject")
    public String deleteErrorSubject(HttpServletRequest httpServletRequest) {
        return subjectService.deleteErrorSubject(httpServletRequest);
    }

    /*
    添加错题
     */
    @RequestMapping("/addErrorSubject")
    public String addErrorSubject(HttpServletRequest httpServletRequest) {
        return subjectService.addErrorSubject(httpServletRequest);
    }

    /*    *//*
    获取当前所有人员
     *//*
    @RequestMapping("/getAllUser")
    public String getAllUser(HttpServletRequest httpServletRequest){
        return userService.getAllUser();
    }*/
    /*
    随机做题
     */
    @RequestMapping("/randomexam")
    public String randomExam(HttpServletRequest httpServletRequest, Model model, @RequestParam("carexam") String carexam, @RequestParam("cartype") String cartype) {
        return subjectService.selectRandomSubject(httpServletRequest);
    }
}
