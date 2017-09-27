package cn.tedu.tedunote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import cn.tedu.tedunote.R;
import cn.tedu.tedunote.entity.Notebook;
import cn.tedu.tedunote.notebook.query.presenter.IQueryNotebookListPresenter;
import cn.tedu.tedunote.notebook.query.presenter.QueryNotebookListPresenterImpl;
import cn.tedu.tedunote.notebook.query.view.IQueryNotebookListView;

public class MainActivity extends BaseActivity implements IQueryNotebookListView{

    IQueryNotebookListPresenter presenter;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new QueryNotebookListPresenterImpl(this);
        presenter.queryNotebookList(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void refreshNotebookList(List<Notebook> notebooks) {
        tvContent.setText("请求数据成功！数据：" + notebooks);
    }

    @Override
    public void showFailure(int state, String message) {
        tvContent.setText("请求数据失败！错误代码：" + state + "，错误原因：" + message);
    }

    @Override
    public void showError(String error) {
        tvContent.setText("请求数据出错！出错的原因：" + error);
    }
}
