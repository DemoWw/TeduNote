package com.wuwei.tedunote.login.presenter;

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
    void login(String username, String password);

}
