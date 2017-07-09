package com.JieCheng.controller;

import jxl.read.biff.BiffException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/10.
 */
@RequestMapping("/admin")
@RestController
public class AdminController extends BaseController {
    /*
    导出模板
     */
    @GetMapping("/exporttemplet")
    public String exportTemplet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("type") String type) throws Exception {
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
    @PostMapping("/importtemplet")
    public String importTemplet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                @RequestParam("file") MultipartFile file, @RequestParam("type") String type) throws IOException, BiffException {
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

}
