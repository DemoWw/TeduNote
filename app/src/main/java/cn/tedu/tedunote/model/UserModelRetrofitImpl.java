package cn.tedu.tedunote.model;

import android.util.Log;

import cn.tedu.tedunote.entity.ResponseBody;
import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.model.service.UserLoginService;
import cn.tedu.tedunote.presenter.OnUserLoginListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tarena on 2017/9/23.
 */
class UserModelRetrofitImpl implements IUserModel {
    private Retrofit mRetrofit;

    public UserModelRetrofitImpl() {
        // [1] 创建Retrofit对象
        String baseUrl = "http://172.60.50.72:8080/note/";
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        mRetrofit = builder.build();
    }

    @Override
    public void login(String username, String password, final OnUserLoginListener onUserLoginListener) {
        // [3] 获得业务接口对象
        UserLoginService service = mRetrofit.create(UserLoginService.class);

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
    public void loginByWeiXin(OnUserLoginListener onUserLoginListener) {
        onUserLoginListener.onLoginError(new Throwable("微信登录功能正在开发中……"));
    }

    @Override
    public void loginByWeiBo(OnUserLoginListener onUserLoginListener) {
        onUserLoginListener.onLoginError(new Throwable("微博登录功能正在开发中……"));
    }

    @Override
    public void register(String username, String nickname, String password, final OnUserRegisterListener onUserRegisterListener) {
        // [3] 获得业务接口对象
        RegisterService service = mRetrofit.create(RegisterService.class);

        // [4] 调用接口中定义的方法
        Call<ResponseBody<User>> call = service.register(username, nickname, password, password);

        // [5] 创建网络访问后的回调接口的对象
        Callback<ResponseBody<User>> callback = new Callback<ResponseBody<User>>() {
            @Override
            public void onResponse(Call<ResponseBody<User>> call, Response<ResponseBody<User>> response) {
                ResponseBody<User> responseBody = response.body();

                // 测试
                Log.d("TEDU", "response body:" + responseBody);

                if (responseBody.getState() == 0) {
                    onUserRegisterListener.onRegisterSuccess(responseBody.getData());
                } else {
                    onUserRegisterListener.onRegisterFailure(responseBody.getState(), responseBody.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody<User>> call, Throwable t) {
                onUserRegisterListener.onRegisterError(t);
            }
        };

        // [6] 将call添加到队列中
        call.enqueue(callback);
    }

    interface RegisterService {
        // 使用POST方式的请求，路径不包括baseUrl
        // 方法的返回值必须是Call<T>，方法名称可自定义，需要提交给服务器的参数在方法中使用@Query(参数名)为前缀进行声明
        @POST("user/regist.do")
        Call<ResponseBody<User>> register(
                @Query("name") String username,
                @Query("nick") String nickname,
                @Query("password") String password,
                @Query("confirm") String passwordConfirm
        );
    }

}
