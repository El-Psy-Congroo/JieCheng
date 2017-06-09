package com.JieCheng.dao.model;

/**
 * Created by Zhang on 2017/5/12.
 */
public class Subject {
    private int subjectid;
    private String content;
    private String info;
    private String subjecttype;
    private String subjectselect;
    private String answer;
    private int grade;
    private String carexam;
    private String cartype;
    private String url;

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSubjecttype() {
        return subjecttype;
    }

    public void setSubjecttype(String subjecttype) {
        this.subjecttype = subjecttype;
    }

    public String getSubjectselect() {
        return subjectselect;
    }

    public void setSubjectselect(String subjectselect) {
        this.subjectselect = subjectselect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getCarexam() {
        return carexam;
    }

    public void setCarexam(String carexam) {
        this.carexam = carexam;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
