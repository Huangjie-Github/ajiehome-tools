package cn.ajiehome.common.exception.entity.bo;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import lombok.Data;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 15:58
 * @Version: 1.0V
 */
@Data
public class ResultBO {
    private Integer code;
    private String msg;

    public ResultBO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultBO newResultBO(CodeType codeType){
        return new ResultBO(codeType.getCode(), codeType.getMsg());
    }
    public static ResultBO newResultBO(CodeType codeType,String message){
        return new ResultBO(codeType.getCode(), message);
    }
    public static ResultBO newResultBO(ApplicationException e){
        return new ResultBO(e.getCode(), e.getMsg());
    }

}
