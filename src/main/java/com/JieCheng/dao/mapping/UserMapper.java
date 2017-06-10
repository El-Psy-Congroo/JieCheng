package com.JieCheng.dao.mapping;

import com.JieCheng.dao.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    boolean addUser(@Param("name") String name, @Param("age") String age, @Param("sex") String sex, @Param("phone") String phone,
                    @Param("idcard") String idcard, @Param("mail") String mail, @Param("loginname") String loginname,
                    @Param("password") String password, @Param("online") String online, @Param("roleid") int roleid, @Param("cartype") String cartype,
                    @Param("carexam") String carexam, @Param("subjectnum") int subjectnum, @Param("begintime") String begintime,
                    @Param("endtime") String endtime, @Param("createtime") String createtime, @Param("createuserid") int createuserid,
                    @Param("createroleid") int createroleid);

    User findByLoginName(@Param("loginName") String loginName);

    boolean modifyPasswordByloginName(@Param("loginName") String loginName, @Param("newPassWord") String newPassWord);

    boolean modifyPasswordByIDCard(@Param("IDCard") String IDCard, @Param("newPassWord") String newPassWord);

    boolean changeOnlineByIDCard(@Param("IDCard") String IDCard, @Param("online") String online);

    boolean changeOnlineByuserId(@Param("userId") Integer userID, @Param("online") String online);

    List<User> getAllUserInfo(@Param("search") String search);

    boolean deleteUsers(@Param("Id") String[] Id);
}
