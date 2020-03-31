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
            message.setSubject("【阿杰之家】");
            message.setText("验证码:"+code+"\n请保存好您的验证码，验证码将在五分钟之后过期。");
            //断言email不能为空
            assert email != null;
            message.setTo(email);
            message.setFrom(userEmail);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new ApplicationException(CodeType.SERVICE_EMAIL_SEND_ERROR);
        }
        return true; g
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
