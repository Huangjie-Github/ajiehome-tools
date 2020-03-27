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

    public void requireToken(String token){
        DecodedJWT decode = JWT.decode(token);
        //Jwt的头部，由于头部被Base64编码过了，所以需要base64逆解编码
        String header = new String(Base64.getDecoder().decode(decode.getHeader()));
        //Jwt的中间部分，理由同上头部
        String payload = new String(Base64.getDecoder().decode(decode.getPayload()));

        JSONObject userJson = JSONObject.parseObject(header);
        String id = userJson.getString("id");
        String userName = userJson.getString("userName");
        Algorithm algorithm = Algorithm.HMAC256(id);
        try {
            //设置验证条件，这里设置了验证的密钥和编码以及验证的签发人
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(userName).build();
            //验证是否匹配
            DecodedJWT verify = jwtVerifier.verify(token);
            //需要的信息获取从头部份获取和中间部分获取
        }catch (Exception e){
            throw  new ApplicationException(CodeType.TOKEN_TIME_OUT);
        }

    }
}
