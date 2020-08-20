package cn.ajiehome.test;

import cn.ajiehome.common.BaseBeanUtils;
import cn.ajiehome.common.md5.Md5Utils;
import cn.ajiehome.common.utils.SnowFlake;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class TestApplicationTests extends BaseBeanUtils {


    @Test
    public void contextLoads() {
        Date date = new Date(1288834974657L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss");
        System.out.println(format.format(date));
        new ArrayList<Integer>();
    }

}
