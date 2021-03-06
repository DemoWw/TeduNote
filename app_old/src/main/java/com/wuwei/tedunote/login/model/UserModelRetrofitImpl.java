package com.wuwei.tedunote.login.model;


import com.wuwei.tedunote.entity.ResponseBody;
import com.wuwei.tedunote.entity.User;
import com.wuwei.tedunote.login.presenter.OnUserLoginListener;
import com.wuwei.tedunote.login.model.service.UserLoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tarena on 2017/9/23.
 */
class UserModelRetrofitImpl implements IUserModel {

    @Override
    public void login(String username, String password, final OnUserLoginListener onUserLoginListener) {
        // [1] 创建Retrofit对象
        String baseUrl = "http://176.233.12.121:8080/note/";
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        // [3] 获得业务接口对象
        UserLoginService service = retrofit.create(UserLoginService.class);

        // [4] 调用接口中定义的方法
        Call<ResponseBody<User>> call = service.login(username, password);

        // [5] 创建网络访问后的回调接口的对象
        Callback<ResponseBody<User>> callback = new Callback<ResponseBody<User>>() {
            @Override
            public void onResponse(Call<ResponseBody<User>> call, Response<ResponseBody<User>> response) {
                ResponseBody<User> responseBody = response.body();
                if (responseBody.getState() == 0) {
                    onUserLoginListener.onLoginSuccess(responseBody.getData());
                } else {
                    onUserLoginListener.onLoginFailure(responseBody.getState(), responseBody.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody<User>> call, Throwable t) {
                onUserLoginListener.onLoginError(t);
            }
        };

        // [6] 将call添加到队列中
        call.enqueue(callback);
    }

    @Override
    public void register(String username, String nickname, String password, OnUserRegisterListener onUserRegisterListener) {

    }

}
