package cn.tedu.tedunote.notebook.query.view;

import java.util.List;

import cn.tedu.tedunote.entity.Notebook;

/**
 * Created by wuwei on 2017/9/27.
 */

public interface IQueryNotebookListView {

    void refreshNotebookList(List<Notebook> notebooks);

    void showFailure(int state, String message);

    void showError(String error);

}
