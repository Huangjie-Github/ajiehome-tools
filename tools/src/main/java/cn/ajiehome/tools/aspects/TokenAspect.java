package cn.ajiehome.tools.aspects;

import cn.ajiehome.tools.annotations.AllowToken;
import cn.ajiehome.tools.enums.CodeType;
import cn.ajiehome.tools.exception.ApplicationException;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 16:52
 * @Version: 1.0V
 */
@Aspect
@Component
public class TokenAspect {
    @Pointcut(value = "@annotation(cn.ajiehome.tools.annotations.AllowToken)")
    private void pointCut(){}

    @Before(value = "pointCut()&&@annotation(allowToken)")
    private void before(AllowToken allowToken){
        //AOP前置通知 advice
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (token==null){
            throw new ApplicationException(CodeType.TOKEN_TIME_OUT);
        }
        //解析token
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
            //验证是否匹配，如果Token不匹配或者超时，此处将抛出异常
            DecodedJWT verify = jwtVerifier.verify(token);
            //需要的信息获取从头部份获取和中间部分获取
        }catch (Exception e){
            throw  new ApplicationException(CodeType.TOKEN_TIME_OUT);
        }
    }
    @After(value = "pointCut()&&@annotation(allowToken)")
    private void after(AllowToken allowToken){
        //AOP后置通知 advice
    }

    @AfterReturning(value = "pointCut()&& @annotation(allowToken)",returning = "o")
    private void alterReturn(AllowToken allowToken,Object o){
        //AOP返回通知 advice
    }

    @AfterThrowing(value = "pointCut()&&@annotation(allowToken)")
    private void alterError(AllowToken allowToken){
        //AOP异常通知 advice
    }
}
