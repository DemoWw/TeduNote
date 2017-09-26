package cn.tedu.tedunote.model;

import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.presenter.OnUserLoginListener;

/**
 * User Model
 * Created by tarena on 2017/9/23.
 */
public interface IUserModel {

    void login(String username, String password, OnUserLoginListener onUserLoginListener);

    void loginByWeiXin(OnUserLoginListener onUserLoginListener);

    void loginByWeiBo(OnUserLoginListener onUserLoginListener);

    void register(String username, String nickname, String password, OnUserRegisterListener onUserRegisterListener);

    interface OnUserRegisterListener {

        void onRegisterSuccess(User user);

        void onRegisterFailure(int state, String message);

        void onRegisterError(Throwable throwable);

    }

}
