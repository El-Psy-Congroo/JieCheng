package com.JieCheng.service.impl;

import com.JieCheng.dao.mapping.AdvertImageMapper;
import com.JieCheng.dao.mapping.SubjectMapper;
import com.JieCheng.dao.mapping.UserImageMapper;
import com.JieCheng.dao.mapping.UserMapper;
import com.JieCheng.service.UserService;
import com.JieCheng.util.*;
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
    @Autowired
    UserImageMapper userImageMapper;
    @Autowired
    AdvertImageMapper advertImageMapper;
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
    @Autowired
    ImageUtil imageUtil;
    /*
    服务层
     */
    @Autowired
    UserService userService;
}
