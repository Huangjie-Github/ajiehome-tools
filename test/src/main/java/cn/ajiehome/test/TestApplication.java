package cn.ajiehome.test;

import cn.ajiehome.tools.utils.base.BaseUtilsBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
public class TestApplication extends BaseUtilsBeanUtils {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
