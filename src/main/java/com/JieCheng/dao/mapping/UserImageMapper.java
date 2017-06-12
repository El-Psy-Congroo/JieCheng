package com.JieCheng.dao.mapping;

import com.JieCheng.dao.model.UserImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserImageMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(UserImage record);

    int insertSelective(UserImage record);

    UserImage selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserImage record);

    int updateByPrimaryKeyWithBLOBs(UserImage record);

    int updateByPrimaryKey(UserImage record);
}