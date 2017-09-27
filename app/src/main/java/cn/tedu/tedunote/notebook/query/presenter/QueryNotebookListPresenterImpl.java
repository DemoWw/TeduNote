package cn.tedu.tedunote.notebook.query.presenter;

import android.content.Context;

import java.util.List;

import cn.tedu.tedunote.entity.Notebook;
import cn.tedu.tedunote.notebook.query.model.INotebookModel;
import cn.tedu.tedunote.notebook.query.model.NotebookModelFactory;
import cn.tedu.tedunote.notebook.query.view.IQueryNotebookListView;

/**
 * Created by wuwei on 2017/9/27.
 */

public class QueryNotebookListPresenterImpl implements IQueryNotebookListPresenter {

    private INotebookModel model;

    private IQueryNotebookListView view;

    public QueryNotebookListPresenterImpl(IQueryNotebookListView view) {
        this.model = NotebookModelFactory.getInstance();
        this.view = view;
    }

    @Override
    public void queryNotebookList(Context context) {
        model.queryNotebookList(context, this);
    }

    @Override
    public void onSuccess(List<Notebook> data) {
        view.refreshNotebookList(data);
    }

    @Override
    public void onFailure(int state, String message) {
        view.showFailure(state, message);
    }

    @Override
    public void onError(Throwable throwable) {
        view.showError(throwable + "");
    }
}
