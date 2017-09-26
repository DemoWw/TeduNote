package cn.tedu.tedunote.presenter;

import cn.tedu.tedunote.model.IUserModel;

/**
 * Created by tarena on 2017/9/26.
 */

public interface IUserRegisterPresenter extends IUserModel.OnUserRegisterListener {

    void register(String username, String nickname, String password, String passwordConfirm);

}
