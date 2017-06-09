package com.JieCheng.util;

import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zhang on 2017/5/23.
 */
@Service
public class ReturnMessage {
    public void returnMessage(HttpServletResponse response, String message) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            ServletOutputStream sos = response.getOutputStream();
            sos.write(message.getBytes("utf-8"));
            sos.flush();
            sos.close();
        } catch (IOException e) {
            message = "{'success':'false','info':'信息传送失败'}";
        }
    }
}
