package cn.tedu.tedunote.presenter;

import cn.tedu.tedunote.ui.IUserLoginView;

/**
 * Created by tarena on 2017/9/23.
 */

public abstract class UserLoginPresenterFactory {

    public static IUserLoginPresenter getInstance(IUserLoginView view) {
        return new UserLoginPresenterImpl(view);
    }

}
