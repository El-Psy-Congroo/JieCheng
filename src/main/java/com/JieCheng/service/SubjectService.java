package com.JieCheng.service;

import jxl.read.biff.BiffException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/11.
 */
public interface SubjectService {
    /*
    导出题目模板
     */
    String exportSubjectTemplet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;

    /*
    题目删除
     */

    /*
    导入数据
     */
    String importTemplet(MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, BiffException;

    /*
    获取当前题目信息列表
     */
    Map<String, Object> getAllSubjectInfo(Integer page, Integer limit);

    /*
    新增收藏题目
     */
    String addCollectSubject(HttpServletRequest httpServletRequest);

    /*
    删除收藏题目
     */
    String deleteCollectSubject(HttpServletRequest httpServletRequest);

    /*
    删除错题
     */
    String deleteErrorSubject(HttpServletRequest httpServletRequest);

    /*
   添加错题
    */
    String addErrorSubject(HttpServletRequest httpServletRequest);

    /*
    获取随机错题
     */
    String selectRandomSubject(HttpServletRequest httpServletRequest);

    /*
    进入答题界面
     */
    String centerPage(HttpServletRequest httpServletRequest, Model model, String examtype, String carexam, String cartype);
}
