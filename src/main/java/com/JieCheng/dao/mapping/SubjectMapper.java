package com.JieCheng.dao.mapping;

import com.JieCheng.dao.model.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhang on 2017/5/12.
 */
@Mapper
public interface SubjectMapper {

    boolean addSubject(@Param("content") String content, @Param("info") String info, @Param("type") String type,
                       @Param("select") String select, @Param("answer") String answer, @Param("grade") int grade,
                       @Param("carexam") String carexam, @Param("cartype") String cartype,
                       @Param("url") String url, @Param("time") String time, @Param("userId") int userId);

    boolean addCollectSubject(@Param("userId") int userId, @Param("subjectId") int subjectId);

    boolean addErrorSubject(@Param("userId") int userId, @Param("subjectId") int subjectId);

    List<Subject> selectRandomSubject(@Param("carexam") String carexam, @Param("cartype") String cartype);

    List<Subject> getAllSubjectInfo(@Param("search") String search);

    List<Subject> getAllMyError(@Param("userId") int userId, @Param("search") String search);

    List<Subject> getAllMyCollect(@Param("userId") int userId, @Param("search") String search);

    boolean deleteSubjects(@Param("Id") String[] Id);

    boolean deleteMyError(@Param("userId") int userId, @Param("Id") String[] Id);

    boolean deleteMyCollect(@Param("userId") int userId, @Param("Id") String[] Id);

    List<Map<String, String>> collectIsExist(@Param("userId") int userId, @Param("subjectId") int subjectId);

    List<Map<String, String>> errorIsExist(@Param("userId") int userId, @Param("subjectId") int subjectId);

}
