package cn.ajiehome.tools.enums;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 15:53
 * @Version: 1.0V
 */
public enum CodeType {
    /**
     * 枚举对象
     */
    SERVICE_ERROR(5001,"服务逻辑操作发生错误"),
    SERVICE_Exception(5002,"服务发生未正常处理异常"),
    TOKEN_TIME_OUT(4002,"Token过时"),
    SERVICE_IO_ERROR(5003,"服务IO操作异常"),
    SERVICE_EMAIL_SEND_ERROR(5004,"服务邮件发送异常");

    private int code;
    private String msg;


    CodeType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
