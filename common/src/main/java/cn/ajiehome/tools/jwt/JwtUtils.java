package cn.ajiehome.tools.jwt;

import cn.ajiehome.tools.enums.CodeType;
import cn.ajiehome.tools.exception.ApplicationException;
import cn.ajiehome.tools.jwt.bo.JwtBeanBO;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 9:16
 * @Version: 1.0V
 */
@Component
public class JwtUtils {


    /**
     *  获取Token，签名由ID代替，形成动态签名
     * @param autoDuration 超时的时间长度
     * @param jwtBeanBO 用户数据
     * @return token
     */
    public String getToken(long autoDuration, JwtBeanBO jwtBeanBO){

        long outLong = System.currentTimeMillis() + autoDuration;
        Date outDate = new Date(outLong);
        Algorithm algorithm = Algorithm.HMAC256(jwtBeanBO.getId().toString());
        HashMap<String, Object> hashMap = new HashMap<>(5);
        hashMap.put("id",jwtBeanBO.getId());
        hashMap.put("userName",jwtBeanBO.getUserName());
        hashMap.put("passWord",jwtBeanBO.getPassWord());
        hashMap.put("userPhone",jwtBeanBO.getUserPhone());
        hashMap.put("userAddress",jwtBeanBO.getUserAddress());

        return JWT.create()
                .withHeader(hashMap)
                .withIssuer(jwtBeanBO.getUserName())
                .withExpiresAt(outDate)
                .sign(algorithm);
    }
}
