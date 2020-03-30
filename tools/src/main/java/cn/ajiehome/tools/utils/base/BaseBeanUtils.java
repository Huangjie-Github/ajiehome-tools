package cn.ajiehome.tools.utils.base;

import cn.ajiehome.tools.advice.GlobalExceptionHandler;
import cn.ajiehome.tools.aspects.TokenAspect;
import cn.ajiehome.tools.jwt.JwtUtils;
import cn.ajiehome.tools.swagger.config.SpringFoxConfig;
import jdk.nashorn.internal.parser.Token;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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
}
