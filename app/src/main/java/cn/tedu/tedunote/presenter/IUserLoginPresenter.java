package cn.tedu.tedunote.presenter;

import android.content.Context;

/**
 * User Presenter
 * Created by tarena on 2017/9/23.
 */
public interface IUserLoginPresenter extends OnUserLoginListener {
    /**
     * 登录，登录后的回调处理在当前接口的父级接口中
     * @param username 用户名
     * @param password 密码
     */
    void login(Context context, String username, String password);

    /**
     * 通过微信登录
     */
    void loginByWeiXin();

    /**
     * 通过微博登录
     */
    void loginByWeiBo();

}
