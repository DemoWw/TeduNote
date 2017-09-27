package cn.tedu.tedunote.notebook.query.presenter;

import android.content.Context;

import java.util.List;

import cn.tedu.tedunote.entity.Notebook;

/**
 * Created by wuwei on 2017/9/27.
 */

public interface IQueryNotebookListPresenter extends OnResponseListener<List<Notebook>> {
    void queryNotebookList(Context context);
}
