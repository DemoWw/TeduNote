package cn.tedu.tedunote.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tedu.tedunote.R;
import cn.tedu.tedunote.util.TextValidator;

/**
 * Created by tarena on 2017/9/23.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @Nullable
    @BindView(R.id.toolbar_title)
    TextView mToolBarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 加载布局
        setContentView(getLayoutResId());
        // 绑定注入
        ButterKnife.bind(this);
        // 初始化控件
        initViews();

        // Toolbar
        initToolbar();
    }

    /**
     * 初始化Toolbar，仅当启用Toolbar后执行
     */
    private void initToolbar() {
        if (getToolbarEnabled()) {
            setSupportActionBar(mToolBar);
            mToolBar.setTitle(null);

            CharSequence toolbarTitle = getToolbarTitle();
            if (toolbarTitle != null) {
                mToolBarTitle.setText(toolbarTitle);
            }

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                // 在标题栏最左侧允许使用“返回”
                actionBar.setDisplayHomeAsUpEnabled(true);
                // 去除标题栏自带的标题文字
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    /**
     * 是否启用Toolbar
     * @return
     */
    protected boolean getToolbarEnabled() {
        return false;
    }

    /**
     * 获取Toolbar中显示的标题，该方法应该在需要使用Toolbar的Activity中被重写
     * @return
     */
    protected CharSequence getToolbarTitle() {
        return null;
    }

    /**
     * 设置Toolbar中显示的文字
     * @param title
     */
    protected final void setToolbarTitle(CharSequence title) {
        if (mToolBarTitle != null) {
            mToolBarTitle.setText(title);
        }
    }

    /**
     * 设置Toolbar中显示的文字
     * @param titleResId
     */
    protected final void setToolBarTitle(int titleResId) {
        if (mToolBarTitle != null) {
            mToolBarTitle.setText(titleResId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 判断id是否为标题栏中的“返回”
        if (item.getItemId() == android.R.id.home) {
            onToolbarBack();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 当Toolbar中的“返回”按钮被点击时回调
     */
    protected void onToolbarBack() {

    }

    /**
     * 获取当前界面需要加载的XML布局的资源索引
     * @return 当前界面需要加载的XML布局的资源索引，例如R.layout.activity_main
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化控件
     */
    protected void initViews() {

    }

}
