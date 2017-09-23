package com.wuwei.tedunote;

/**
 * Created by wuwei on 2017/9/23.
 */

public interface OnUserLoginListener {

    void onLoginSuccess(User user);

    void onLoginFailure(int state, String message);

    void onLoginError(Throwable throwable);
}
