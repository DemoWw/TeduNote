package com.wuwei.tedunote.login.view;

import android.support.annotation.Nullable;

import com.wuwei.tedunote.entity.User;

/**
 * Created by wuwei on 2017/9/23.
 */

public interface IUserLoginView {
    /**
     * 显示登录成功的相关信息
     * @param user 登录的用户的数据
     */
    void showLoginSuccess(User user);
    /**
     * 设置用户名错误
     * @param message 错误信息
     */
    void setUsernameError(String message);
    /**
     * 设置密码错误
     * @param message 错误信息
     */
    void setPasswordError(String message);
    /**
     * 设置登录错误，该错误出现的原因通常是网络访问本身出错了
     * @param throwable 出错的异常，可能为Null
     */
    void showLoginError(@Nullable Throwable throwable);
}
