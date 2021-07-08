package cn.ajiehome.common.utils.entity;


public class ResultBT {
    private Integer code;
    private Object result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResultBT(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    public ResultBT() {
    }
}
