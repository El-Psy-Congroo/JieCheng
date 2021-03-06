package com.JieCheng.service.impl;

import com.JieCheng.dao.model.AdvertImage;
import com.JieCheng.dao.model.User;
import com.JieCheng.dao.model.UserImage;
import com.JieCheng.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jxl.read.biff.BiffException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/9.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Override
    public String checkUser(String loginName, String passWord, String page, Model model, HttpServletRequest httpServletRequest) {
        User user;
        user = userService.getUserByLoginName(loginName);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (user == null) {
            model.addAttribute("message", "该用户不存在");
            return "login";
        }
        if (!user.getPassword().equals(passWord)) {
            model.addAttribute("message", "密码错误");
            return "login";
        }
        if (user.getOnline().equals("Y")) {
            model.addAttribute("message", "用户状态为在线");
            return "login";
        }
        try {
            if (df.parse(user.getEndTime()).getTime() < (new Date().getTime())) {
                model.addAttribute("message", "账号已过期");
                return "login";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //向Session添加用户信息
        httpServletRequest.getSession().setAttribute("user", user);
        model.addAttribute("user", user);
        if (user.getRoleId() == 1) {
            userMapper.changeOnlineByuserId(user.getUserId(), "Y");
        }
        if (page.equals("admin")) {
            return "admin";
        } else {
            return "index";
        }

    }

    @Override
    public User getUserByLoginName(String loginName) {
        User user = userMapper.findByLoginName(loginName);
        return user;
    }

    @Override
    public String modifyPassWord(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String newPassWord = httpServletRequest.getParameter("newPassWord");
        String oldPassWord = httpServletRequest.getParameter("oldPassWord");
        if (!user.getPassword().equals(oldPassWord)) {
            return "原密码错误";
        }
        boolean result = userMapper.modifyPasswordByloginName(user.getLoginName(), newPassWord);
        if (result) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @Override
    public String findPassWord(String IDcard, String newPassWord) {
        String message;
        boolean result = userMapper.modifyPasswordByIDCard(IDcard, newPassWord);
        if (result) {
            message = "密码已重置";
        } else {
            message = "身份证号码输入错误";
        }/*
        returnMessage.returnMessage(httpServletResponse, message);*/
        return message;
    }

    @Override
    public String exportUserTemplet(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user.getRoleId() != 4) {
            return "权限不足";
        }
        String[] label = {"姓名", "年龄", "性别", "手机号", "身份证号码", "邮箱", "登陆名", "密码", "角色(学员/区域代理/加盟伙伴/管理员)", "驾考类型", "驾考科目", "有效期开始(yyyy-MM-dd)", "有效期结束(yyyy-MM-dd)"};
        excelUtil.excelTempletExport("人员模板", label, response);
        return "导出成功";
    }

    @Override
    public String importTemplet(MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, BiffException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        StringBuilder result = new StringBuilder("");
        if (user.getRoleId() != 4) {
            return "权限不足";
        }
        User[] users = excelUtil.templetToUser(file);
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int num = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().length() >= 6) {
                result.append("用户姓名不能超过6个字End;");
                continue;
            } else if (!regex.NumPositiveInt(users[i].getAge())) {
                result.append("用户年龄应为正整数End;");
                continue;
            } else if (!regex.checkPhone(users[i].getPhone())) {
                result.append("用户手机号不合法End;");
                continue;
            } else if (!regex.checkIDCard(users[i].getIDCard())) {
                result.append("用户身份证号码不合法End;");
                continue;
            } else if (!regex.checkDate(users[i].getBeginTime()) || !regex.checkDate(users[i].getEndTime())) {
                result.append("有限期时间不合规范End;");
                continue;
            } else {
                result.append("成功End;");
            }
            users[i].setSubjectNum(0);
            users[i].setCreateUserId(user.getUserId());
            users[i].setCreateRoleId(user.getRoleId());
            users[i].setCreateTime(sdf.format(dt));
            userMapper.addUser(users[i].getName(), users[i].getAge(), users[i].getSex(), users[i].getPhone(),
                    users[i].getIDCard(), users[i].getMail(), users[i].getLoginName(), users[i].getPassword(), users[i].getOnline(),
                    users[i].getRoleId(), users[i].getCarType(), users[i].getCarExam(), users[i].getSubjectNum(),
                    users[i].getBeginTime(), users[i].getEndTime(), users[i].getCreateTime(), users[i].getCreateUserId(),
                    users[i].getCreateRoleId());
            num++;
        }
        try {
            excelUtil.returnResoult(result.toString(), file, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Success";
    }

    @Override
    public String changeOnline(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String IDCard, String status) {
        String message;
        boolean b = userMapper.changeOnlineByIDCard(IDCard, status);
        if (b) {
            message = "用户状态已重置";
        } else {
            message = "身份证号码错误，找不到相应用户";
        }
        returnMessage.returnMessage(httpServletResponse, message);
        return null;
    }

    @Override
    public Map<String, Object> getAllUserInfo(String search, Integer page, Integer limit) {
        if (page == null || limit == null) {
            page = 1;
            limit = 20;
        }
        if (search.equals("all")) {
            search = "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(page, limit);
        List<User> list = userMapper.getAllUserInfo(search);
        Page<User> useInfos = (Page<User>) list;
        long total = useInfos.getTotal();
        map.put("total", total);
        map.put("infos", useInfos);
        return map;
    }

    @Override
    public String deleteUsers(String ids) {
        String[] id = ids.split(",");
        if (userMapper.deleteUsers(id)) {
            return "删除成功";
        } else {
            return "删除失败";
        }

    }

    @Override
    public String addUserImage(MultipartFile file, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String fileName= file.getOriginalFilename();
        String fileType=getFileType(fileName);
        if (!fileType.equals("jpeg") && !fileType.equals("png") && !fileType.equals("jpg")) {
            return "图片格式不合法";
        }
        if (file.getSize()>2097152){
            return "图片需小于2M";
        }
        String code = imageUtil.imageToString(file, fileType);
        UserImage userImage = new UserImage();
        userImage.setImageContent(code);
        userImage.setImageType(fileType);
        userImage.setImageSize(String.valueOf(file.getSize()));
        userImage.setUserId(user.getUserId());

        UserImage isExist = userImageMapper.selectByPrimaryKey(user.getUserId());
        if (isExist == null || isExist.getImageContent() == null) {
            userImageMapper.insert(userImage);
        }else {
            userImageMapper.updateByPrimaryKey(userImage);
        }
        return null;
    }

    private String getFileType(String fileName){
        String []fileInfo=fileName.split("\\.");
        return fileInfo[1];
    }

    @Override
    public String getUserImage(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        UserImage userImage = userImageMapper.selectByPrimaryKey(user.getUserId());
        if (userImage == null || userImage.getImageContent() == null) {
            userImage = userImageMapper.selectByPrimaryKey(0);
        }
        String src=userImage.getImageContent();
        return src;
    }

    @Override
    public Map<String, Object> getAdvertImage(String search, Integer page, Integer limit) {
        if (page == null || limit == null) {
            page = 1;
            limit = 20;
        }
        if (search.equals("all")) {
            search = "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(page, limit);
        List<AdvertImage> list = advertImageMapper.getAllImageId();
        Page<AdvertImage> advertImages = (Page<AdvertImage>) list;
        long total = advertImages.getTotal();
        map.put("total", total);
        map.put("infos", advertImages);
        return map;
    }

    @Override
    public String getImage(Integer id) {
        return advertImageMapper.selectByPrimaryKey(id).getImageContent();
    }
}
