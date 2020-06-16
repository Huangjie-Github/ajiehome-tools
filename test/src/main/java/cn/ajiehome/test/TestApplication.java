package cn.ajiehome.test;

import cn.ajiehome.common.BaseBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 */
@EnableSwagger2
@SpringBootApplication
public class TestApplication extends BaseBeanUtils {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
