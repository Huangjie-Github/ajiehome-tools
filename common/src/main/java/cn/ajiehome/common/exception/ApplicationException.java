package cn.ajiehome.common.exception;

import cn.ajiehome.common.enums.CodeType;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 15:50
 * @Version: 1.0V
 */
public class ApplicationException extends RuntimeException {
    private int code;
    private String msg;

    public ApplicationException(CodeType codeType) {
        this.code = codeType.getCode();
        this.msg = codeType.getMsg();
    }
    public ApplicationException(CodeType codeType,String msg) {
        this.code = codeType.getCode();
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
