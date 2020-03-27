package cn.ajiehome.tools.utils.base;

import cn.ajiehome.tools.advice.GlobalExceptionHandler;
import cn.ajiehome.tools.jwt.JwtUtils;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 9:17
 * @Version: 1.0V
 */
@Component
public abstract class BaseUtilsBeanUtils extends SpringBootServletInitializer {

    @Bean
    public JwtUtils getJwtUtils(){
        return new JwtUtils();
    }

    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }
}
