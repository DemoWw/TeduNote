package cn.tedu.tedunote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.tedu.tedunote.R;
import cn.tedu.tedunote.ui.LoginActivity;
import cn.tedu.tedunote.util.SettingUtils;

/**
 * Created by tarena on 2017/9/26.
 */
public class Page03Fragment extends BaseFragment {
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page03, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_start)
    public void doStart() {
        // 激活登录界面
        startActivity(new Intent(getActivity(), LoginActivity.class));
        // 设置为不再是第1次运行
        SettingUtils.setIsFirstRun(getActivity(), false);
        // 销毁当前Activity
        getActivity().finish();
    }


}
