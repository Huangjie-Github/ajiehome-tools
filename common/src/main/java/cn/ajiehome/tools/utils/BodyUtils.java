package cn.ajiehome.tools.utils;

import cn.ajiehome.tools.enums.CodeType;
import cn.ajiehome.tools.exception.ApplicationException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.channels.ClosedChannelException;
import java.util.Arrays;

/**
 * @Author: HuangJie
 * @Date: 2020/3/30 15:33
 * @Version: 1.0V
 */
@Component
public class BodyUtils {

    public JSONObject bodyJson(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] temp = new byte[1024 ];
            int read;
            while ((read=inputStream.read(temp))!=-1){
                stream.write(temp,0,read);
            }
            inputStream.close();
            stream.close();
            return JSONObject.parseObject(stream.toString());
        } catch (IOException e) {
            throw new ApplicationException(CodeType.SERVICE_IO_ERROR);
        }
    }
}
