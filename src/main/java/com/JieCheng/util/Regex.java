package com.JieCheng.util;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Created by Zhang on 2017/5/25.
 */
@Service
public class Regex {
    /*
    检测输入是否为1-4之间的数字
     */
    public boolean NumOneToFour(String content){
        String pattern = "[1-4]";
        boolean isMatch = Pattern.matches(pattern, content);
        if(isMatch && content.length()==1){
            return true;
        }else {
            return false;
        }
    }
    /*
    检查输入是否为正整数
     */
    public boolean NumPositiveInt(String content){
        String pattern = "^[1-9]\\d*$";
        boolean isMatch = Pattern.matches(pattern, content);
        if(isMatch){
            return true;
        }else {
            return false;
        }
    }
    /*
    检测手机号码合法性
     */
    public boolean checkPhone(String content){
        String pattern = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        boolean isMatch = Pattern.matches(pattern, content);
        if(isMatch){
            return true;
        }else {
            return false;
        }
    }
    /*
    检测身份证号码合法性
     */
    public boolean checkIDCard(String content) {
        String pattern = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
        boolean isMatch = Pattern.matches(pattern, content);
        if (isMatch) {
            return true;
        } else {
            return false;
        }
    }
    /*
    检测日期合法性
     */
    public boolean checkDate(String content){
        String pattern = "^\\d{4}-\\d{1,2}-\\d{1,2}";
        boolean isMatch = Pattern.matches(pattern, content);
        if (isMatch) {
            return true;
        } else {
            return false;
        }
    }
}
