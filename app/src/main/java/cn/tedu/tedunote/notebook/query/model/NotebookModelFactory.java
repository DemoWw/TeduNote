package cn.tedu.tedunote.notebook.query.model;

/**
 * Created by wuwei on 2017/9/27.
 */

public class NotebookModelFactory {
    public static INotebookModel getInstance() {
        return new NotebookModelImpl();
    }
}
