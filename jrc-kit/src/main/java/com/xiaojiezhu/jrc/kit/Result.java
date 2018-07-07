package com.xiaojiezhu.jrc.kit;

/**
 * response data,the real data in 'data' field
 * @param <T>
 * @author xiaojie.zhu
 */
public class Result<T> {



    /**
     * 0 == success
     */
    private int code;

    /**
     * The request return message,such as error message
     */
    private String msg;

    /**
     * Real data
     */
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
