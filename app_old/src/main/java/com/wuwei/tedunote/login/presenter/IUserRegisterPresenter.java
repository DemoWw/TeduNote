package com.wuwei.tedunote.login.presenter;

import com.wuwei.tedunote.login.model.IUserModel;

/**
 * Created by wuwei on 2017/9/26.
 */

public interface IUserRegisterPresenter extends IUserModel.OnUserRegisterListener {
    void register(String username, String nickname, String password, String passwordConfirm);
}
