package cn.tedu.tedunote.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.tedu.tedunote.R;
import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.presenter.IUserLoginPresenter;
import cn.tedu.tedunote.presenter.UserLoginPresenterFactory;
import cn.tedu.tedunote.ui.BaseActivity;
import cn.tedu.tedunote.ui.IUserLoginView;

public class LoginActivity extends BaseActivity implements TextWatcher, IUserLoginView {
    @BindView(R.id.til_username_wrapper)
    TextInputLayout tilUsernameWrapper;
    @BindView(R.id.til_password_wrapper)
    TextInputLayout tilPasswordWrapper;
    @BindView(R.id.btn_login)
    Button btnLogin;
    IUserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 需要执行的主要业务已经在父类BaseActivity中完成

        // 获取Presenter对象
        presenter = UserLoginPresenterFactory.getInstance(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        // 为2个输入框添加输入字符时的监听
        tilUsernameWrapper.getEditText().addTextChangedListener(this);
        tilPasswordWrapper.getEditText().addTextChangedListener(this);
    }

    @OnClick(R.id.root)
    public void onRootViewClick() {
        // 当点击根节点控件（即整个屏幕的任何位置）时收起软键盘
        hideSoftInputMethod();
    }

    @OnClick(R.id.btn_login)
    public void doLogin(View view) {
        // 获取用户输入的数据
        String username = tilUsernameWrapper.getEditText().getText().toString().trim();
        String password = tilPasswordWrapper.getEditText().getText().toString();

        // 向服务器提交用户名与密码，并获取登录结果
        presenter.login(this, username, password);

        // 禁用登录按钮
        btnLogin.setEnabled(false);

        // 收起软键盘
        hideSoftInputMethod();
    }

    @OnClick(R.id.btn_register)
    public void doRegister() {
        // 跳转到注册界面，且当前界面不销毁
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btn_login_by_weixin)
    public void doLoginByWeiXin() {
        // 通过Presenter实现微信登录
        presenter.loginByWeiXin();
    }

    @OnClick(R.id.btn_login_by_weibo)
    public void doLoginByWeiBo() {
        // 通过Presenter实现微信登录
        presenter.loginByWeiBo();
    }

    /**
     * 收起软键盘
     */
    private void hideSoftInputMethod() {
        // 固定做法
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    @Override
    public void showLoginSuccess(User user) {
        // 暂时使用Toast提示
        Toast.makeText(this, "登录成功！" + user, Toast.LENGTH_SHORT).show();

        // 启用登录按钮
        btnLogin.setEnabled(true);

        // 跳转到主界面
        startActivity(new Intent(this, MainActivity.class));
        // TODO 销毁自身
    }

    @Override
    public void setUsernameError(String message) {
        // 启用错误，因为每次提示错误后都已禁用错误
        tilUsernameWrapper.setErrorEnabled(true);
        // 提示
        tilUsernameWrapper.setError(message);

        // 启用登录按钮
        btnLogin.setEnabled(true);
    }

    @Override
    public void setPasswordError(String message) {
        // 启用错误，因为每次提示错误后都已禁用错误
        tilPasswordWrapper.setErrorEnabled(true);
        // 提示
        tilPasswordWrapper.setError(message);

        // 启用登录按钮
        btnLogin.setEnabled(true);
    }

    @Override
    public void showLoginError(@Nullable Throwable throwable) {
        // 提示
        Toast.makeText(this, "未知错误！" + throwable, Toast.LENGTH_SHORT).show();

        // 启用登录按钮
        btnLogin.setEnabled(true);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // 清空错误文字
        tilUsernameWrapper.setError("");
        // 禁用错误，否则错误信息占用的屏幕空间依然存在一片空白处
        tilUsernameWrapper.setErrorEnabled(false);

        // 清空错误文字
        tilPasswordWrapper.setError("");
        // 禁用错误，否则错误信息占用的屏幕空间依然存在一片空白处
        tilPasswordWrapper.setErrorEnabled(false);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

}
