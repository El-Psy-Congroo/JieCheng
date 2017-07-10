package com.JieCheng.dao.mapping;

import com.JieCheng.dao.model.AdvertImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdvertImageMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(AdvertImage record);

    int insertSelective(AdvertImage record);

    AdvertImage selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(AdvertImage record);

    int updateByPrimaryKeyWithBLOBs(AdvertImage record);

    int updateByPrimaryKey(AdvertImage record);

    List<AdvertImage> getAllImageId();
}