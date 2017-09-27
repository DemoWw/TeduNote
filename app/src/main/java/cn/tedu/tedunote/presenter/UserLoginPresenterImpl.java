package cn.tedu.tedunote.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import cn.tedu.tedunote.entity.ResponseBody;
import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.model.IUserModel;
import cn.tedu.tedunote.model.UserModelFactory;
import cn.tedu.tedunote.ui.IUserLoginView;
import cn.tedu.tedunote.util.TextValidator;

/**
 * Created by tarena on 2017/9/23.
 */
class UserLoginPresenterImpl implements IUserLoginPresenter {
    // Presenter固定特性：持有model的引用
    private IUserModel model;
    // Presenter固定特性：持有view的引用
    private IUserLoginView view;

    public UserLoginPresenterImpl(IUserLoginView view) {
        model = UserModelFactory.getInstance();
        this.view = view;
    }

    @Override
    public void login(Context context, String username, String password) {
        // Presenter固定特性：业务逻辑判断
        // 验证数据的有效性
        int checkResult = TextValidator.checkUsername(username);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setUsernameError(TextValidator.Result.TEXT[checkResult]);
            return;
        }
        checkResult = TextValidator.checkPassword(password);
        if (checkResult != TextValidator.Result.OK) {
            // Presenter固定特性：使得view完成相应的界面更新操作
            view.setPasswordError(TextValidator.Result.TEXT[checkResult]);
            return;
        }
        // Presenter固定特性：调用model中的功能
        model.login(context, username, password, this);
    }

    @Override
    public void loginByWeiXin() {
        model.loginByWeiXin(this);
    }

    @Override
    public void loginByWeiBo() {
        model.loginByWeiBo(this);
    }

    @Override
    public void onLoginSuccess(User user) {
        // Presenter固定特性：使得view完成相应的界面更新操作
        view.showLoginSuccess(user);
    }

    @Override
    public void onLoginFailure(int state, String message) {
        // Presenter固定特性：使得view完成相应的界面更新操作
        if (state == ResponseBody.STATE_USERNAME_NOT_EXISTS) {
            view.setUsernameError(message);
        } else if (state == ResponseBody.STATE_PASSWORD_NOT_MATCH) {
            view.setPasswordError(message);
        }
    }

    @Override
    public void onLoginError(@Nullable Throwable throwable) {
        // Presenter固定特性：使得view完成相应的界面更新操作
        view.showLoginError(throwable);
    }
}
