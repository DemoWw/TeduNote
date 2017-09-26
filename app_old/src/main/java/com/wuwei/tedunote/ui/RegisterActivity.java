package com.wuwei.tedunote.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wuwei.tedunote.tedunote.R;

import butterknife.BindView;

/**
 * Created by tarena on 2017/9/26.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        // Toolbar
        setSupportActionBar(mToolBar);
        mToolBar.setTitle(null);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 在标题栏最左侧允许使用“返回”
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 去除标题栏自带的标题文字
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 判断id是否为标题栏中的“返回”
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
