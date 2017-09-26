package com.wuwei.tedunote.entity;

/**
 * Created by wuwei on 2017/9/23.
 */

public class ResponseBody<T> {
    private int state;
    private String message;
    private T data;

    /**
     * 状态：正确
     */
    public static final int STATE_OK = 0;
    /**
     * 状态：用户名不存在
     */
    public static final int STATE_USERNAME_NOT_EXISTS = 2;
    /**
     * 状态：密码错误
     */
    public static final int STATE_PASSWORD_NOT_MATCH = 3;

    public void setState(int state) {
        this.state = state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getState() {

        return state;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
