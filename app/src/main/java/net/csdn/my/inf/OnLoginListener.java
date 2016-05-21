package net.csdn.my.inf;

import net.csdn.my.bean.User;

/**
 * 监听登陆信息的接口，有两个回调方法
 * Created by lenovo on 2016/5/10.
 */
public interface OnLoginListener{
    void loginSuccess(User user);
    void loginFailed();
}
