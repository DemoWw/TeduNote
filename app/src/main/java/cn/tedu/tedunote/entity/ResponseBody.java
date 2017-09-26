package cn.tedu.tedunote.entity;

/**
 * 由服务器端发回的响应的数据格式
 * Created by tarena on 2017/9/23.
 */
public class ResponseBody<T> {
    /**
     * 状态
     */
    private int state;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据
     */
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

    public ResponseBody() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "state:" + state +
                ", message:" + message +
                ", data:" + data +
                '}';
    }
}
