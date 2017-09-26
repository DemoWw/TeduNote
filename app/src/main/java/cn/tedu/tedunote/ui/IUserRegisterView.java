package cn.tedu.tedunote.ui;

import cn.tedu.tedunote.entity.User;

/**
 * Created by tarena on 2017/9/26.
 */
public interface IUserRegisterView {

    void setUsernameError(String message);

    void setNicknameError(String message);

    void setPasswordError(String message);

    void setPasswordConfirmError(String message);

    void processRegisterSuccess(User user);

    void showRegisterFailure(String message);

    void showRegisterError(Throwable throwable);

}
