package com.wuwei.tedunote;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wuwei on 2017/9/22.
 */

public interface UserLoginService {

    @POST("user/login.do")
    Call<String> login(@Query("name") String username, @Query("password") String password);

}
