package cn.ajiehome.tools.md5;

import cn.ajiehome.tools.enums.CodeType;
import cn.ajiehome.tools.exception.ApplicationException;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: HuangJie
 * @Date: 2020/4/7 9:11
 * @Version: 1.0V
 */
public class Md5Utils {
    public String md5(File file){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte [] bytes = new byte[1024];
            int read = 0;
            while ((read=(fileInputStream.read(bytes)))!=-1){
                stream.write(bytes,0,read);
            }
            fileInputStream.close();
            stream.close();
            byte[] byteArray = stream.toByteArray();
            md5.update(byteArray);
            byte[] digest = md5.digest();
            BigInteger bigInteger = new BigInteger(1,digest);
            //生成MD5的进制数
            return bigInteger.toString(10);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            throw new ApplicationException(CodeType.SERVICE_ERROR, "MD5转换失败");
        }
    }
}
