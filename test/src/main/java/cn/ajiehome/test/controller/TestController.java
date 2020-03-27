package cn.ajiehome.test.controller;

import cn.ajiehome.tools.annotations.AllowToken;
import cn.ajiehome.tools.jwt.bo.JwtBeanBO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author: HuangJie
 * @Date: 2020/3/26 15:53
 * @Version: 1.0V
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/token")

    public String allToken(@RequestBody JwtBeanBO jwtBeanBO){

        throw new RuntimeException("异常");



    }
}
