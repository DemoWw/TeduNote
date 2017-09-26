package com.wuwei.tedunote.login.model;

import com.wuwei.tedunote.login.presenter.OnUserLoginListener;

/**
 * Created by wuwei on 2017/9/23.
 */

public interface IUserModel {
    void login(String username, String password, OnUserLoginListener onUserLoginListener);

    void register(String username, String nickname, String password, OnUserRegisterListener onUserRegisterListener);

    interface OnUserRegisterListener {

    }
}
