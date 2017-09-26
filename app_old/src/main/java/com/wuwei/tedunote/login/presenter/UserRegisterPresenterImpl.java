package com.wuwei.tedunote.login.presenter;


import com.wuwei.tedunote.login.model.IUserModel;
import com.wuwei.tedunote.login.model.UserModelFactory;
import com.wuwei.tedunote.login.view.IUseRegisterView;
import com.wuwei.tedunote.utils.TextValidator;

/**
 * Created by wuwei on 2017/9/26.
 */

public class UserRegisterPresenterImpl implements IUserRegisterPresenter {

    private IUserModel model;

    private IUseRegisterView view;

    public UserRegisterPresenterImpl(IUseRegisterView view) {
        this.model = UserModelFactory.getInstance();
        this.view = view;
    }

    @Override
    public void register(String username, String nickname, String password, String passwordConfirm) {
        // Presenter固定特性：业务逻辑判断
        // 验证数据的有效性
        int checkResult = TextValidator.checkUsername(username);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setUsernameError(TextValidator.Result.MESSAGE[checkResult]);
            return;
        }
        checkResult = TextValidator.checkNickname(nickname);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setNicknameError(TextValidator.Result.MESSAGE[checkResult]);
            return;
        }
        checkResult = TextValidator.checkPassword(password);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setPasswordError(TextValidator.Result.MESSAGE[checkResult]);
            return;
        }
        boolean b = password.equals(passwordConfirm);
        if (!b) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setPasswordConfirmError("错误！两次输入的密码不一致！");
            return;
        }
        // Presenter固定特性：调用model中的功能
        model.register(username, nickname, password, this);
    }
}
