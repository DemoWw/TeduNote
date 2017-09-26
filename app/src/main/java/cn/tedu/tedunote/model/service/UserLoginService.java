package cn.tedu.tedunote.model.service;

import cn.tedu.tedunote.entity.ResponseBody;
import cn.tedu.tedunote.entity.User;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit接口，必须是这个样子
 * Created by tarena on 2017/9/22.
 */
public interface UserLoginService {
    // 使用POST方式的请求，路径不包括baseUrl
    // 方法的返回值必须是Call<T>，方法名称可自定义，需要提交给服务器的参数在方法中使用@Query(参数名)为前缀进行声明
    @POST("user/login.do")
    Call<ResponseBody<User>> login(@Query("name") String u, @Query("password") String p);
}
