package com.wuwei.tedunote;

/**
 * Created by wuwei on 2017/9/23.
 */

public class ResponseBody<T> {
    private int state;
    private String message;
    private T data;

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
