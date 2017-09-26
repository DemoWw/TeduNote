package cn.tedu.tedunote.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.tedu.tedunote.R;
import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.presenter.IUserRegisterPresenter;
import cn.tedu.tedunote.presenter.UserRegisterPresenterImpl;

/**
 * Created by tarena on 2017/9/26.
 */
public class RegisterActivity extends BaseActivity implements IUserRegisterView {
    @BindString(R.string.register)
    String register;
    @BindView(R.id.til_username_wrapper)
    TextInputLayout tilUsernameWrapper;
    @BindView(R.id.til_nickname_wrapper)
    TextInputLayout tilNicknameWrapper;
    @BindView(R.id.til_password_wrapper)
    TextInputLayout tilPasswordWrapper;
    @BindView(R.id.til_password_confirm_wrapper)
    TextInputLayout tilPasswordConfirmWrapper;
    @BindView(R.id.btn_register)
    Button btnRegister;
    IUserRegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new UserRegisterPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean getToolbarEnabled() {
        return true;
    }

    @Override
    protected CharSequence getToolbarTitle() {
        return register;
    }

    @Override
    protected void onToolbarBack() {
        finish();
    }

    @OnClick(R.id.btn_register)
    public void doRegister() {
        // 获取用户输入的数据
        String username = tilUsernameWrapper.getEditText().getText().toString().trim();
        String nickname = tilNicknameWrapper.getEditText().getText().toString().trim();
        String password = tilPasswordWrapper.getEditText().getText().toString();
        String passwordConfirm = tilPasswordConfirmWrapper.getEditText().getText().toString();

        // 通过Presenter对象提交注册
        presenter.register(username, nickname, password, passwordConfirm);

        // 禁用注册按钮
        btnRegister.setEnabled(false);

        // 收起软键盘
        hideSoftInputMethod();
    }

    @Override
    public void setUsernameError(String message) {
        // 启用错误，因为每次提示错误后都已禁用错误
        tilUsernameWrapper.setErrorEnabled(true);
        // 提示
        tilUsernameWrapper.setError(message);

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    @Override
    public void setNicknameError(String message) {
        // 启用错误，因为每次提示错误后都已禁用错误
        tilNicknameWrapper.setErrorEnabled(true);
        // 提示
        tilNicknameWrapper.setError(message);

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    @Override
    public void setPasswordError(String message) {
// 启用错误，因为每次提示错误后都已禁用错误
        tilPasswordWrapper.setErrorEnabled(true);
        // 提示
        tilPasswordWrapper.setError(message);

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    @Override
    public void setPasswordConfirmError(String message) {
        // 启用错误，因为每次提示错误后都已禁用错误
        tilPasswordConfirmWrapper.setErrorEnabled(true);
        // 提示
        tilPasswordConfirmWrapper.setError(message);

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    @Override
    public void processRegisterSuccess(User user) {
        Toast.makeText(this, "注册成功！" + user, Toast.LENGTH_SHORT).show();

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    @Override
    public void showRegisterFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    @Override
    public void showRegisterError(Throwable throwable) {
        // 提示
        Toast.makeText(this, "未知错误！" + throwable, Toast.LENGTH_SHORT).show();

        // 启用注册按钮
        btnRegister.setEnabled(true);
    }

    /**
     * 收起软键盘
     */
    private void hideSoftInputMethod() {
        // 固定做法
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}
