package com.wuwei.tedunote.login.presenter;


import com.wuwei.tedunote.login.view.IUserLoginView;

/**
 * Created by tarena on 2017/9/23.
 */

public abstract class UserLoginPresenterFactory {

    public static IUserLoginPresenter getInstance(IUserLoginView view) {
        return new UserLoginPresenterImpl(view);
    }

}
