package cn.ajiehome.common.advice;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.ResultBO;
import cn.ajiehome.common.utils.entity.ResultBT;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 15:44
 * @Version: 1.0V
 */
@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    private final static Logger logger = Logger.getLogger("GlobalExceptionHandler->");

    @ExceptionHandler(Exception.class)
    public Object handler(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        if (e instanceof ApplicationException) {
            response.setStatus(201);
            return ResultBO.newResultBO((ApplicationException) e);
        } else {
            response.setStatus(202);
            return ResultBO.newResultBO(CodeType.SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
       // 调用beforeBodyWrite为ture，不调用为false
        return true;
    }
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultBO) {
            return body;
        }
        return new ResultBT(200, body);
    }
}
