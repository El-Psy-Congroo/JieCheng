package com.JieCheng.service;

import com.JieCheng.dao.model.User;
import jxl.read.biff.BiffException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/9.
 */
public interface UserService {

    String checkUser(String loginName, String passWord, String page, Model model, HttpServletRequest httpServletRequest);

    /*
    通过登录名获取用户
     */
    User getUserByLoginName(String loginName);

    /*
    修改密码
     */
    String modifyPassWord(HttpServletRequest httpServletRequest);

    /*
    重置密码
     */
    String findPassWord(String IDcard, String newPassWord);

    /*
    导出人员模板
    */
    String exportUserTemplet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;

    /*
    导入数据
    */
    String importTemplet(MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, BiffException;

    /*
    修改用户在线状态
     */
    String changeOnline(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String IDCard, String status);

    /*
    获取所有用户
     */
    Map<String, Object> getAllUserInfo(String search, Integer page, Integer limit);

    /*
    删除选中用户
     */
    String deleteUsers(String ids);

    /*
    添加用户照片
     */
    String addUserImage(MultipartFile file, HttpServletRequest httpServletRequest);

    /*
    获取用户照片
     */
    String getUserImage(HttpServletRequest userId);
}
