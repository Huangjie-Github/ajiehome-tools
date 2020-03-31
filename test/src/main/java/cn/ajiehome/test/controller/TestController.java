package cn.ajiehome.test.controller;

import cn.ajiehome.tools.annotations.AllowToken;
import cn.ajiehome.tools.emails.NetEaseEmailCode;
import cn.ajiehome.tools.jwt.JwtUtils;
import cn.ajiehome.tools.jwt.bo.JwtBeanBO;
import cn.ajiehome.tools.utils.BodyUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: HuangJie
 * @Date: 2020/3/26 15:53
 * @Version: 1.0V
 */
@Api(tags = {"Test测试接口"})
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private BodyUtils bodyUtils;
    @Autowired
    private NetEaseEmailCode netEaseEmailCode;

    @ApiOperation(value = "获取token",notes = "为每一位用户生成特定的Token")
    @PostMapping("/token")
    public String allToken(@RequestBody  @ApiParam(name = "用户数据对象",value = "直接传入JSON主体数据，不需要jwtBeanBO的JSON头",required = true) JwtBeanBO jwtBeanBO){
        return jwtUtils.getToken(1000 * 60 * 60 * 24, jwtBeanBO);
    }

    @AllowToken
    @ApiOperation(value = "邮箱验证码",notes = "给特定用户发送邮箱验证码")
    @ApiImplicitParam(name = "email",value = "用来给用户发送验证码的邮箱号码",required = true,paramType = "body",dataType = "String")
    @PostMapping("/email/code")
    public Boolean emailCode(HttpServletRequest request){
        JSONObject bodyJson = bodyUtils.bodyJson(request);
        String email = bodyJson.getString("email");
        return netEaseEmailCode.sendCode(email);
    }
}
