package cn.tedu.tedunote.presenter;

import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.model.IUserModel;
import cn.tedu.tedunote.model.UserModelFactory;
import cn.tedu.tedunote.ui.IUserRegisterView;
import cn.tedu.tedunote.util.TextValidator;

/**
 * Created by tarena on 2017/9/26.
 */

public class UserRegisterPresenterImpl implements IUserRegisterPresenter {
    // Presenter固定特性：持有model的引用
    private IUserModel model;
    // Presenter固定特性：持有view的引用
    private IUserRegisterView view;

    public UserRegisterPresenterImpl(IUserRegisterView view) {
        model = UserModelFactory.getInstance();
        this.view = view;
    }

    @Override
    public void register(String username, String nickname, String password, String passwordConfirm) {
        // 验证数据的有效性
        int checkResult = TextValidator.checkUsername(username);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setUsernameError(TextValidator.Result.TEXT[checkResult]);
            return;
        }
        checkResult = TextValidator.checkNickname(nickname);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setNicknameError(TextValidator.Result.TEXT[checkResult]);
            return;
        }
        checkResult = TextValidator.checkPassword(password);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setPasswordError(TextValidator.Result.TEXT[checkResult]);
            return;
        }
        boolean b = password.equals(passwordConfirm);
        if (!b) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setPasswordConfirmError("错误！两次输入的密码不一致！");
            return;
        }

        // 通过model提交注册的请求
        model.register(username, nickname, password, this);
    }

    @Override
    public void onRegisterSuccess(User user) {
        view.processRegisterSuccess(user);
    }

    @Override
    public void onRegisterFailure(int state, String message) {
        view.showRegisterFailure(message);
    }

    @Override
    public void onRegisterError(Throwable throwable) {
        view.showRegisterError(throwable);
    }

}
