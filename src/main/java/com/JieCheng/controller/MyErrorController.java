package com.JieCheng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Zhang on 2017/6/10.
 */
@RestController
public class MyErrorController extends BaseController {
    /*
    删除错题
     */
    @RequestMapping("/deleteMyError")
    public String deleteMyError(HttpServletRequest httpServletRequest, @RequestParam("ids") String ids) {
        return subjectService.deleteMyError(httpServletRequest, ids);
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
    public Map<String, Object> getAllMyError(HttpServletRequest httpServletRequest, @RequestParam("search") String search,
                                             @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return subjectService.getAllMyError(httpServletRequest, search, page, limit);
    }
}
