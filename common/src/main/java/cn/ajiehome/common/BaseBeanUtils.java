package cn.ajiehome.common;

import cn.ajiehome.common.advice.GlobalExceptionHandler;
import cn.ajiehome.common.aspects.TokenAspect;
import cn.ajiehome.common.emails.NetEaseEmailCode;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.swagger.config.SpringFoxConfig;
import cn.ajiehome.common.utils.BodyUtils;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 9:17
 * @Version: 1.0V
 */
@Component
public abstract class BaseBeanUtils extends SpringBootServletInitializer {

    @Bean
    public JwtUtils getJwtUtils(){
        return new JwtUtils();
    }

    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    public TokenAspect getTokenAspect(){
        return new TokenAspect();
    }

    @Bean
    public SpringFoxConfig getSpringFoxConfig(){
        return new SpringFoxConfig();
    }

    @Bean
    public NetEaseEmailCode getNetEaseEmailCode(){
        return new NetEaseEmailCode();
    }

    @Bean
    public BodyUtils getGetBodyUtils(){
        return new BodyUtils();
    }

}