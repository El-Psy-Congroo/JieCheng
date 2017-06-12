package com.JieCheng.service.impl;

import com.JieCheng.dao.model.Subject;
import com.JieCheng.dao.model.User;
import com.JieCheng.service.SubjectService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jxl.read.biff.BiffException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/11.
 */
@Service
public class SubjectServiceImpl extends BaseServiceImpl implements SubjectService {

    @Override
    public String exportSubjectTemplet(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user.getRoleId() != 4) {
            return "权限不足";
        }
        String[] label = {"题目内容", "题目说明", "题目类型(单选/多选/判断)", "题目选项(以';'分隔)", "答案", "分值(仅数字)", "科目(1/2/3/4)", "车型"};
        excelUtil.excelTempletExport("题目模板", label, response);
        return "导出成功";
    }

    @Override
    public String deleteSubjects(String ids) {
        String[] id = ids.split(",");
        if (subjectMapper.deleteSubjects(id)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String importTemplet(MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, BiffException {
        StringBuilder result = new StringBuilder("");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user.getRoleId() != 4) {
            return "权限不足";
        }
        Subject[] subjects = excelUtil.templetToSubjects(file);
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int num = 0;
        for (int i = 0; i < subjects.length; i++) {
            String subjecTtype = subjects[i].getSubjecttype();
            if (subjects[i].getContent().length() >= 1000) {
                result.append("题目长度不能超过1000字End;");
                continue;
            } else if (subjects[i].getInfo().length() >= 100) {
                result.append("题目讲解不能超过100字End;");
                continue;
            } else if (!(subjecTtype.equals("单选") || subjecTtype.equals("多选") || subjecTtype.equals("判断"))) {
                result.append("题目类型仅支持单选题;多选题;判断题End;");
                continue;
            } else if (!regex.NumOneToFour(subjects[i].getCarexam())) {
                result.append("驾考科目请填写'1，2，3，4'End;");
                continue;
            } else {
                result.append("成功End;");
            }
            subjectMapper.addSubject(subjects[i].getContent(), subjects[i].getInfo(), subjects[i].getSubjecttype(), subjects[i].getSubjectselect(),
                    subjects[i].getAnswer(), subjects[i].getGrade(), subjects[i].getCarexam(), subjects[i].getCartype(),
                    subjects[i].getUrl(), sdf.format(dt), user.getUserId());
        }
        try {
            excelUtil.returnResoult(result.toString(), file, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getAllSubjectInfo(String search, Integer page, Integer limit) {
        if (page == null || limit == null) {
            page = 1;
            limit = 20;
        }
        if (search.equals("all")) {
            search = "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(page, limit);
        List<Subject> list = subjectMapper.getAllSubjectInfo(search);
        Page<Subject> useInfos = (Page<Subject>) list;
        long total = useInfos.getTotal();
        map.put("total", total);
        map.put("infos", useInfos);
        return map;
    }

    @Override
    public String addCollectSubject(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String subjectId = httpServletRequest.getParameter("subjectId");
        int userId = user.getUserId();
        boolean result = subjectMapper.addCollectSubject(userId, Integer.parseInt(subjectId));
        if (result) {
            return "新增成功";
        } else {
            return "添加失败";
        }
    }

    @Override
    public String deleteMyCollect(HttpServletRequest httpServletRequest, String ids) {
        String[] id = ids.split(",");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        int userId = user.getUserId();
        if (subjectMapper.deleteMyCollect(userId, id)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public Map<String, Object> getAllMyCollect(HttpServletRequest httpServletRequest, String search, Integer page, Integer limit) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        int userId = user.getUserId();
        if (search.equals("all")) {
            search = "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(page, limit);
        List<Subject> list = subjectMapper.getAllMyCollect(userId, search);
        Page<Subject> useInfos = (Page<Subject>) list;
        long total = useInfos.getTotal();
        map.put("total", total);
        map.put("infos", useInfos);
        return map;
    }

    @Override
    public String deleteMyError(HttpServletRequest httpServletRequest, String ids) {
        String[] id = ids.split(",");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        int userId = user.getUserId();
        if (subjectMapper.deleteMyError(userId, id)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @Override
    public String addErrorSubject(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String subjectId = httpServletRequest.getParameter("subjectId");
        boolean result = subjectMapper.addErrorSubject(user.getUserId(), Integer.parseInt(subjectId));
        if (result) {
            return "新增成功";
        } else {
            return "新增失败";
        }
    }

    @Override
    public Map<String, Object> getAllMyError(HttpServletRequest httpServletRequest,
                                             String search, Integer page, Integer limit) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        int userId = user.getUserId();
        if (search.equals("all")) {
            search = "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(page, limit);
        List<Subject> list = subjectMapper.getAllMyError(userId, search);
        Page<Subject> useInfos = (Page<Subject>) list;
        long total = useInfos.getTotal();
        map.put("total", total);
        map.put("infos", useInfos);
        return map;

    }

    @Override
    public String selectRandomSubject(HttpServletRequest httpServletRequest) {
        String carExam = httpServletRequest.getParameter("carexam");
        String carType = httpServletRequest.getParameter("cartype");
        Subject subject;
        switch (carExam) {
            case "科目一":
                carExam = "1";
                break;
            case "科目二":
                carExam = "2";
                break;
            case "科目三":
                carExam = "3";
                break;
            case "科目四":
                carExam = "4";
                break;
            default:
                carExam = "0";
                break;
        }
        subject = subjectMapper.selectRandomSubject(carExam, carType);
        return jsonLittleData.ObjectToJson(subject);
    }

    @Override
    public String centerPage(HttpServletRequest httpServletRequest, Model model, String examtype, String carexam, String cartype) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        model.addAttribute("user", user);
        switch (carexam) {
            case "1":
                model.addAttribute("carexam", "科目一");
                break;
            case "2":
                model.addAttribute("carexam", "科目二");
                break;
            case "3":
                model.addAttribute("carexam", "科目三");
                break;
            case "4":
                model.addAttribute("carexam", "科目四");
                break;
        }
        model.addAttribute("cartype", cartype);
        if (examtype.equals("random")) {
            return "centerRandom";
        } else {
            return "centerExam";
        }
    }
}
