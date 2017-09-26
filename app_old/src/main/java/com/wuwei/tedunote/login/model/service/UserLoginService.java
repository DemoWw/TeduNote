package com.wuwei.tedunote.login.model.service;

import com.wuwei.tedunote.entity.ResponseBody;
import com.wuwei.tedunote.entity.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wuwei on 2017/9/22.
 */

public interface UserLoginService {

    @POST("user/login.do")
    Call<ResponseBody<User>> login(@Query("name") String username, @Query("password") String password);

}
