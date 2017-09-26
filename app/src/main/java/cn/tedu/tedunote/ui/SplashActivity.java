package cn.tedu.tedunote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import cn.tedu.tedunote.R;
import cn.tedu.tedunote.fragment.Page01Fragment;
import cn.tedu.tedunote.fragment.Page02Fragment;
import cn.tedu.tedunote.fragment.Page03Fragment;
import cn.tedu.tedunote.util.SettingUtils;

/**
 * Created by tarena on 2017/9/26.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.vp_container)
    ViewPager vpContainer;
    FragmentPagerAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断是否是第1次运行，如果不是，则直接跳转到登录界面
        boolean isFirstRun = SettingUtils.isFirstRun(this);
        if (!isFirstRun) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void initViews() {
        adapter = new InnerFragmentPagerAdapter(getSupportFragmentManager());
        vpContainer.setAdapter(adapter);
    }

    private class InnerFragmentPagerAdapter extends FragmentPagerAdapter {

        public InnerFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0 : fragment = new Page01Fragment(); break;
                case 1 : fragment = new Page02Fragment(); break;
                case 2 : fragment = new Page03Fragment(); break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
