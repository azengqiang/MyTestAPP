package net.csdn.my.view;

import net.csdn.my.bean.User;

/**
 * 用户登陆的视图接口
 * 对于view的接口，观察功能上的操作，然后需要考虑的问题：
 * 1.该操作需要什么？（getUserName，getPassword）
 * 2.该操作的结果反馈？（toMainActivity，showFailedError）
 * 3.操作过程中的友好反馈？（showLoading，hideLoading）
 * Created by neijiang on 2016/5/10.
 */
public interface IUserLoginView {
    //登陆需要获取用户名和密码
    public String getUserName();

    public String getPassword();

    //登陆是一个耗时操作，为了界面友好，需要有个进度条
    public void showLoading();

    public void hideLoading();

    //登陆成功或者失败的处理回调
    public void toMainActivity(User user);

    public void showFailedError();
}
