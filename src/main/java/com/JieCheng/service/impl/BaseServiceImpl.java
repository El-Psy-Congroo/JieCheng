package com.JieCheng.service.impl;

import com.JieCheng.dao.mapping.SubjectMapper;
import com.JieCheng.dao.mapping.UserMapper;
import com.JieCheng.service.UserService;
import com.JieCheng.util.ExcelUtil;
import com.JieCheng.util.JsonLittleData;
import com.JieCheng.util.Regex;
import com.JieCheng.util.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Zhang on 2017/5/9.
 */
public class BaseServiceImpl {

    /*
    数据持久层
     */
    @Autowired
    UserMapper userMapper;
    @Autowired
    SubjectMapper subjectMapper;
    /*
    工具类
     */
    @Autowired
    ExcelUtil excelUtil;
    @Autowired
    JsonLittleData jsonLittleData;
    @Autowired
    ReturnMessage returnMessage;
    @Autowired
    Regex regex;
    /*
    服务层
     */
    @Autowired
    UserService userService;
}
