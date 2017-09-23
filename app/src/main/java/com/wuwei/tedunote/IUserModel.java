package com.wuwei.tedunote;

/**
 * Created by wuwei on 2017/9/23.
 */

public interface IUserModel {
    void login(String username, String password, OnUserLoginListener onUserLoginListener);
}
