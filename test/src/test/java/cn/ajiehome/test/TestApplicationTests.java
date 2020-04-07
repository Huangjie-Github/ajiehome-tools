package cn.ajiehome.test;

import cn.ajiehome.tools.md5.Md5Utils;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.File;

@SpringBootTest
public class TestApplicationTests {


    @Test
    public void contextLoads() {

        File file1 = new File("E:\\images\\2020\\4\\2\\695267429294706688.jpg");
        File file2 = new File("C:\\Users\\Administrator\\Desktop\\test.jpg");

        Md5Utils md5Utils = new Md5Utils();
        String md5 = md5Utils.md5(file1);
        String md51 = md5Utils.md5(file2);
        System.out.println(md5+"=="+md51);
        System.out.println(md5.equals(md51));
    }

}
