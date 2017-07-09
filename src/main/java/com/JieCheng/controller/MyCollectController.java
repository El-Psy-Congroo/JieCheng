package com.JieCheng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Zhang on 2017/6/10.
 */
@RequestMapping("/user")
@RestController
public class MyCollectController extends BaseController {
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
    public String deleteMyCollect(HttpServletRequest httpServletRequest, @RequestParam("ids") String ids) {
        return subjectService.deleteMyCollect(httpServletRequest, ids);
    }

    /*
    获取我的收藏
     */
    @RequestMapping("/getAllMyCollect")
    public Map<String, Object> getAllMyCollect(HttpServletRequest httpServletRequest, @RequestParam("search") String search,
                                               @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return subjectService.getAllMyCollect(httpServletRequest, search, page, limit);
    }
}
