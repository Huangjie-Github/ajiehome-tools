package cn.ajiehome.tools.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: HuangJie
 * @Date: 2020/3/30 10:39
 * @Version: 1.0V
 */
public class SpringFoxConfig{
    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("*.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * 创建api的基本信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("API 接口")
                .termsOfServiceUrl("http://www.jiekemaike.cn")
                .version("1.0")
                .build();
    }
}