package com.JieCheng.controller;

import com.JieCheng.service.SubjectService;
import com.JieCheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Zhang on 2017/5/10.
 */
public class BaseController {
    @Autowired
    UserService userService;
    @Autowired
    SubjectService subjectService;
}
