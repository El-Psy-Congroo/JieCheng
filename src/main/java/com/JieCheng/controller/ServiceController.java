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
    public Map<String, Object> getAllUserInfo(@RequestParam("search") String search,
                                              @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return userService.getAllUserInfo(search, page, limit);
    }

    /*
    删除选中人员
     */
    @RequestMapping("/deleteUsers")
    public String deleteUsers(@RequestParam("ids") String ids) {
        return userService.deleteUsers(ids);
    }

    /*
    获取当前题目信息列表
     */
    @RequestMapping("/getAllSubjectInfo")
    public Map<String, Object> getAllSubjectInfo(@RequestParam("search") String search,
                                                 @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return subjectService.getAllSubjectInfo(search, page, limit);
    }

    /*
    删除选中题目
     */
    @RequestMapping("/deleteSubjects")
    public String deleteSubjects(@RequestParam("ids") String ids) {
        return subjectService.deleteSubjects(ids);
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
    @RequestMapping("/deleteMyCollect")
    public String deleteMyCollect(HttpServletRequest httpServletRequest,@RequestParam("ids") String ids){
        return subjectService.deleteMyCollect(httpServletRequest,ids);
    }
    /*
    获取我的收藏
     */
    @RequestMapping("/getAllMyCollect")
    public Map<String, Object> getAllMyCollect(HttpServletRequest httpServletRequest,@RequestParam("search") String search,
                                             @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return subjectService.getAllMyCollect(httpServletRequest, search, page, limit);
    }
    /*
    删除错题
     */
    @RequestMapping("/deleteMyError")
    public String deleteMyError(HttpServletRequest httpServletRequest,@RequestParam("ids") String ids){
        return subjectService.deleteMyError(httpServletRequest,ids);
    }
    /*
    添加错题
     */
    @RequestMapping("/addErrorSubject")
    public String addErrorSubject(HttpServletRequest httpServletRequest) {
        return subjectService.addErrorSubject(httpServletRequest);
    }
    /*
    获取我的错题
     */
    @RequestMapping("/getAllMyError")
    public Map<String, Object> getAllMyError(HttpServletRequest httpServletRequest,@RequestParam("search") String search,
                                @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return subjectService.getAllMyError(httpServletRequest,search, page, limit);
    }
    /*
    随机做题
     */
    @RequestMapping("/randomexam")
    public String randomExam(HttpServletRequest httpServletRequest, Model model, @RequestParam("carexam") String carexam, @RequestParam("cartype") String cartype) {
        return subjectService.selectRandomSubject(httpServletRequest);
    }
}
