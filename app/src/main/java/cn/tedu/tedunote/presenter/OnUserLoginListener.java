package cn.tedu.tedunote.presenter;

import cn.tedu.tedunote.entity.User;

/**
 * 登录后的回调接口
 * Created by tarena on 2017/9/23.
 */
public interface OnUserLoginListener {
    /**
     * 登录成功
     * @param user 成功登录的用户的信息
     */
    void onLoginSuccess(User user);

    /**
     * 登录失败，可能因为用户名不存在，或密码错误
     * @param state 登录失败的错误代码，2表示用户名不存在，3表示密码错误
     * @param message 登录失败的提示信息，该信息是由服务器端程序返回的
     */
    void onLoginFailure(int state, String message);

    /**
     * 登录出现未知错误
     * @param throwable
     */
    void onLoginError(Throwable throwable);

}
