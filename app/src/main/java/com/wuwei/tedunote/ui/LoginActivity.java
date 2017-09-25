package com.wuwei.tedunote.ui;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;

import com.wuwei.tedunote.entity.User;
import com.wuwei.tedunote.login.presenter.UserLoginPresenter;
import com.wuwei.tedunote.login.view.IUserLoginView;
import com.wuwei.tedunote.tedunote.R;
import com.wuwei.tedunote.utils.TextValidator;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements TextWatcher, IUserLoginView {

    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mTilUsername.getEditText().addTextChangedListener(this);
        mTilPassword.getEditText().addTextChangedListener(this);
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        String username = mTilUsername.getEditText().getText().toString();
        String password = mTilPassword.getEditText().getText().toString();

        int result = TextValidator.checkUsername(username);
        if (result != TextValidator.Result.OK) {
            mTilUsername.setErrorEnabled(true);
            mTilUsername.setError(TextValidator.Result.MESSAGE[result]);
            return;
        }

        result =TextValidator.checkPassword(password);
        if (result != TextValidator.Result.OK) {
            mTilPassword.setErrorEnabled(true);
            mTilPassword.setError(TextValidator.Result.MESSAGE[result]);
            return;
        }


        // 向服务器提交用户名与密码，并获取登录结果
        // http://172.60.50.66:8080/note/user/login.do
        // POST
        // name:demo
        // password:123456
        // {"state":0,
        // "message":null,
        //        "data":{"id":"48595f52-b22c-4485-9244-f4004255b972",
        //        "name":"demo",
        //        "password":"86c99f04c9162ce2bc1be721b69bf187","token":null,"nick":null}}

        UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);
        userLoginPresenter.login(username, password);
    }


    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mTilUsername.setError("");
        mTilUsername.setErrorEnabled(false);
        mTilPassword.setError("");
        mTilPassword.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


    @Override
    public void showLoginSuccess(User user) {
        Toast.makeText(LoginActivity.this, "登录成功！" + user, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsernameError(String message) {
        Toast.makeText(LoginActivity.this, "错误：" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError(String message) {
        Toast.makeText(LoginActivity.this, "错误：" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(@Nullable Throwable throwable) {
        // 当无法响应时
        Toast.makeText(getApplicationContext(), "请求登录失败！[" + throwable +"]", Toast.LENGTH_SHORT).show();
    }
}
