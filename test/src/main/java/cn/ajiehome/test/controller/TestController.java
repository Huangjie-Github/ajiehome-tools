package cn.ajiehome.test.controller;

import cn.ajiehome.tools.annotations.AllowToken;
import cn.ajiehome.tools.jwt.bo.JwtBeanBO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: HuangJie
 * @Date: 2020/3/26 15:53
 * @Version: 1.0V
 */
@Api(tags = {"Test测试接口"})
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "获取token",notes = "为每一位用户生成特定的Token")
    @PostMapping("/token")
    @AllowToken
    public String allToken(@RequestBody  @ApiParam(name = "用户数据对象",value = "直接传入JSON主体数据，不需要jwtBeanBO的JSON头",required = true) JwtBeanBO jwtBeanBO){

        System.out.println("AOP开始");

        return "true";

    }
}
