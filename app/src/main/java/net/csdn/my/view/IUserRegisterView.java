package net.csdn.my.view;


import net.csdn.my.bean.RegisterUser;

/**
 *
 * Created by neijiang on 2016/5/11.
 */
public interface IUserRegisterView {
    //注册需要获取用户名和密码
    public String getUserName();

    public String getPassword();

    //注册成功或者失败的处理回调
    public void toMainActivity(RegisterUser registerUser);

    public void showFailedError();
}