package cn.tedu.tedunote.notebook.query.presenter;

/**
 * Created by wuwei on 2017/9/27.
 */

public interface OnResponseListener<T> {

    void onSuccess(T data);

    void onFailure(int state, String message);

    void onError(Throwable throwable);
}
