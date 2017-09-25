package com.wuwei.tedunote.login.presenter;

import com.wuwei.tedunote.entity.User;
import com.wuwei.tedunote.login.model.UserModelImpl;
import com.wuwei.tedunote.login.model.IUserModel;
import com.wuwei.tedunote.login.view.IUserLoginView;

/**
 * Created by wuwei on 2017/9/23.
 */

public class UserLoginPresenter implements OnUserLoginListener {

    private IUserModel model;

    private IUserLoginView view;

    public UserLoginPresenter(IUserLoginView view) {
        this.model = new UserModelImpl();
        this.view = view;
    }

    public void login(String username, String password) {
        model.login(username, password, this);
    }

    @Override
    public void onLoginSuccess(User user) {
        view.showLoginSuccess(user);
    }

    @Override
    public void onLoginFailure(int state, String message) {
        if (state == 2) {
            view.setUsernameError(message);
        } else if (state == 3) {
            view.setPasswordError(message);
        }
    }

    @Override
    public void onLoginError(Throwable throwable) {
        view.showLoginError(throwable);
    }
}
