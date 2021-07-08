package cn.ajiehome.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.File;
import java.util.List;

/**
 * @author Jie
 */

@Slf4j
@Configuration
public class GoWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Value("${local.image.url}")
    private String localImageUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File(localImageUrl);
        System.out.println("文件夹是否存在=>：" + localImageUrl + "=>" + file.exists());
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            log.info("图片文件夹创建=>" + mkdirs);
        }
        registry.addResourceHandler("/image/**").addResourceLocations("file:/" + localImageUrl);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 关闭String类型的转换
        converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}
