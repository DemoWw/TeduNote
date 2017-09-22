package com.wuwei.tedunote;

import android.media.MediaCodec;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.Toast;

import com.wuwei.tedunote.tedunote.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity implements TextWatcher{

    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mTilUsername.getEditText().addTextChangedListener(this);
        mTilPassword.getEditText().addTextChangedListener(this);
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        String username = mTilUsername.getEditText().getText().toString();
        String password = mTilPassword.getEditText().getText().toString();

        if (username.length() < 4) {
            mTilUsername.setErrorEnabled(true);
            mTilUsername.setError("错误：长度应为4~16之间");
            return;
        }
        String regex = "[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()) {

        } else {
            mTilUsername.setErrorEnabled(true);
            mTilUsername.setError("错误：必须由字母、数字、下划线组成，不允许使用数字作为第1个字符！");
            return;
        }

        if (password.length() < 4) {
            mTilPassword.setErrorEnabled(true);
            mTilPassword.setError("错误：长度应为4~16之间");
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

        // [1]创建Retrofit
        String baseUrl = "http://176.233.12.114:8080/note/";
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        // 处理响应结果，把响应的输入流读取出来得到String
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();
        // [3]创建执行业务的对象
        UserLoginService service = retrofit.create(UserLoginService.class);
        // [4]执行业务中的自定义方法
        Call<String> call = service.login(username, password);
        // [5]将需要执行的业务添加到队列中
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // 当得到响应时
                // 结果可能是：
                // state:2; message:用户名错误
                // state:3; message:密码错误
                // state:0; message:null; date:{id, name, password, token, nick}
                String responseBody = response.body();
                // 解析Json
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    int state = jsonObject.getInt("state");
                    if (state == 0) {
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "错误：" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // 当无法响应时
                Toast.makeText(getApplicationContext(), "请求登录失败！[" + t +"]", Toast.LENGTH_SHORT).show();
            }
        });

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
}
