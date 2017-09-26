package cn.tedu.tedunote.model;

import cn.tedu.tedunote.presenter.OnUserLoginListener;

/**
 * Created by tarena on 2017/9/23.
 */
class UserModelVolleyImpl implements IUserModel {
    @Override
    public void login(String username, String password, OnUserLoginListener onUserLoginListener) {
        // 未实现，仅用于举例工厂模式
    }

    @Override
    public void loginByWeiXin(OnUserLoginListener onUserLoginListener) {
        // 未实现，仅用于举例工厂模式
    }

    @Override
    public void loginByWeiBo(OnUserLoginListener onUserLoginListener) {
        // 未实现，仅用于举例工厂模式
    }

    @Override
    public void register(String username, String nickname, String password, OnUserRegisterListener onUserRegisterListener) {
        // 未实现，仅用于举例工厂模式
    }
}
