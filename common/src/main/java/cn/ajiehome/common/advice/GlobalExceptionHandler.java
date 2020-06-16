package cn.ajiehome.common.advice;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 15:44
 * @Version: 1.0V
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handler(Exception e){
        if(e instanceof ApplicationException){
            return ResultBO.newResultBO((ApplicationException) e);
        }else {
            return ResultBO.newResultBO(CodeType.SERVICE_Exception,e.getMessage());
        }
    }
}
