package com.JieCheng.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

/**
 * Created by Zhang on 2017/6/12.
 */
@Service
public class ImageUtil {
    public String imageToString(MultipartFile file, String imageType) {
        StringBuilder code = new StringBuilder("data:image/" + imageType + ";base64,");
        if (!file.isEmpty()) {
            try {
                BASE64Encoder encoder = new BASE64Encoder();
                // 通过base64来转化图片
                code.append(encoder.encode(file.getBytes()));
                return code.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
