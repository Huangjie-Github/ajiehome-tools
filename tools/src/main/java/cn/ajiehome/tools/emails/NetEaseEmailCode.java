package cn.ajiehome.tools.emails;

import cn.ajiehome.tools.enums.CodeType;
import cn.ajiehome.tools.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: HuangJie
 * @Date: 2020/3/30 14:40
 * @Version: 1.0V
 */
@Component
public class NetEaseEmailCode {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userEmail;

    public Boolean sendCode(String email) {
        String code = generatedCode();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("验证码");
            message.setText(code);
            message.setTo(email);
            message.setFrom(userEmail);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new ApplicationException(CodeType.SERVICE_ERROR);
        }
        return true;
    }

    private String generatedCode() {
        StringBuilder buffer = new StringBuilder();
        Random random = new Random();
        int codeSize = 6;
        for (int i = 0; i < codeSize; i++) {
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }

}
