package com.wuwei.tedunote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuwei on 2017/9/23.
 */

public class UserModelImpl implements IUserModel {
    @Override
    public void login(String username, String password, final OnUserLoginListener onUserLoginListener) {
        // [1]创建Retrofit对象
        String baseUrl = "http://176.233.12.114:8080/note/";
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        // [3]创建执行业务的对象
        UserLoginService service = retrofit.create(UserLoginService.class);

        // [4]执行业务中自定义的方法
        Call<ResponseBody<User>> call = service.login(username, password);

        // [5]将业务添加到队列中
        call.enqueue(new Callback<ResponseBody<User>>() {
            @Override
            public void onResponse(Call<ResponseBody<User>> call, Response<ResponseBody<User>> response) {
                ResponseBody<User> responseBody = response.body();
                int result = responseBody.getState();
                if (result == 0) {
                    onUserLoginListener.onLoginSuccess(responseBody.getData());
                } else {
                    onUserLoginListener.onLoginFailure(result, responseBody.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody<User>> call, Throwable t) {
                onUserLoginListener.onLoginError(t);
            }
        });
    }
}
