package cn.tedu.tedunote.notebook.query.model;

import android.content.Context;

import cn.tedu.tedunote.notebook.query.presenter.OnResponseListener;

/**
 * Created by wuwei on 2017/9/27.
 */

public interface INotebookModel {
    void queryNotebookList(Context context, OnResponseListener onResponseListener);
}
