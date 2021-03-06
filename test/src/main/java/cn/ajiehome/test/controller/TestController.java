package cn.ajiehome.test.controller;

import cn.ajiehome.common.annotations.AllowToken;
import cn.ajiehome.common.emails.NetEaseEmailCode;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import cn.ajiehome.common.utils.BodyUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    public String allToken(@RequestBody  @ApiParam(name = "用户数据对象",
            value = "传入对应的用户数据：id为用户id，passWord：为用户密码，userAddress：为用户地址，userName：为用户名，userPhone：为用户手机号",
            required = true) JwtBeanBO jwtBeanBO){
        return jwtUtils.getToken(1000 * 60 * 60 * 24, jwtBeanBO);
    }

    @ApiOperation(value = "邮箱验证码",notes = "给特定用户发送邮箱验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token",value = "token",required = true,paramType = "header",dataType = "String"),
        @ApiImplicitParam(name = "email",value = "用来给用户发送验证码的邮箱号码",required = true,paramType = "body",dataType = "String")

    })
    @PostMapping("/email/code")
    @AllowToken
    public Boolean emailCode(HttpServletRequest request){
        JSONObject bodyJson = bodyUtils.bodyJson(request);
        String email = bodyJson.getString("email");
        return netEaseEmailCode.sendCode(email);
    }

    @ApiOperation(value = "测试接口",notes = "单纯的测试接口，无任何逻辑操作，不需要任何参数，包括Token")
    @PostMapping("/")
    public Boolean test(){
       return true;
    }

    @ApiOperation(value = "测试接口",notes = "单纯的测试接口，无任何逻辑操作，不需要任何参数，包括Token")
    @GetMapping(value = "/")
    public String testGet(){
//        HashMap<Integer, String> hashMap = new HashMap<>();
//        hashMap.put(1,"测试");
//        hashMap.put(2,"测试");
        return "A";
    }
}