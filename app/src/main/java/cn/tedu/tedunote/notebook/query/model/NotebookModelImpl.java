package cn.tedu.tedunote.notebook.query.model;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.util.Log;

import java.util.List;

import cn.tedu.tedunote.entity.Notebook;
import cn.tedu.tedunote.entity.ResponseBody;
import cn.tedu.tedunote.entity.User;
import cn.tedu.tedunote.notebook.query.presenter.OnResponseListener;
import cn.tedu.tedunote.util.SettingUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import static cn.tedu.tedunote.util.Server.BASE_URL;
import static cn.tedu.tedunote.util.Server.QUERY_NOTEBOOK_LIST_PARAM_UID;
import static cn.tedu.tedunote.util.Server.QUERY_NOTEBOOK_LIST_URL;

/**
 * Created by wuwei on 2017/9/27.
 */

public class NotebookModelImpl implements INotebookModel {

    private Retrofit mRetrofit;

    public NotebookModelImpl() {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("TEDU", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(loggingInterceptor);
        OkHttpClient client = okHttpClientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void queryNotebookList(Context context, final OnResponseListener onResponseListener) {
        User user = SettingUtils.getUserInfo(context);
        String userId = user.getId();
        String cookie = SettingUtils.getUserCookie(context);

        QueryNotebookListService service = mRetrofit.create(QueryNotebookListService.class);
        Call<ResponseBody<List<Notebook>>> call = service.query(cookie, userId);
        Callback<ResponseBody<List<Notebook>>> callback = new Callback<ResponseBody<List<Notebook>>>() {
            @Override
            public void onResponse(Call<ResponseBody<List<Notebook>>> call, Response<ResponseBody<List<Notebook>>> response) {
                ResponseBody<List<Notebook>> responseBody = response.body();
                if (responseBody.getState() == 0) {
                    onResponseListener.onSuccess(responseBody.getData());
                } else {
                    onResponseListener.onFailure(responseBody.getState(), responseBody.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody<List<Notebook>>> call, Throwable t) {
                onResponseListener.onError(t);
            }
        };
        call.enqueue(callback);
    }

    interface QueryNotebookListService {
        @GET(QUERY_NOTEBOOK_LIST_URL)
        Call<ResponseBody<List<Notebook>>> query(
                @Header("Cookie") String cookie,
                @Query(QUERY_NOTEBOOK_LIST_PARAM_UID) String userId);
    }
}
