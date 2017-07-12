package net.csdn.my.Presenter;

import android.content.Context;
import android.os.Handler;

import net.csdn.my.bean.RegisterUser;
import net.csdn.my.bean.User;
import net.csdn.my.inf.OnLoginListener;
import net.csdn.my.inf.OnRegisterListener;
import net.csdn.my.model.IUserModel;
import net.csdn.my.model.UserModel;
import net.csdn.my.view.IUserLoginView;
import net.csdn.my.view.IUserRegisterView;


/**
 * 负责用户model和view之间的交互
 * 功能:有一个登陆的功能，该功能需要调用登陆view和model来实现。
 * 1.通过构造函数，获取登陆的view和model
 * 2.定义login方法，首先通过登陆view显示等待进度条，
 * 然后调用登陆model处理登陆逻辑，实现登陆按钮的回调事件
 * Created by lenovo on 2016/5/11.
 */
public class UserPresenter {
    private IUserModel userModel;
    private IUserLoginView userLoginView;
    private IUserRegisterView userRegisterView;
    private Handler mHandler = new Handler();

    public UserPresenter(IUserLoginView userLoginView, Context context) {
        this.userLoginView = userLoginView;
        this.userModel = new UserModel(context);
    }

    public UserPresenter(IUserRegisterView userRegisterView, Context context) {
        this.userRegisterView = userRegisterView;
        this.userModel = new UserModel(context);
    }

    public void register() {
        userModel.register(userRegisterView.getUserName(), userRegisterView.getPassword(), new OnRegisterListener() {
            @Override
            public void registerSuccess(final RegisterUser registerUser) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userRegisterView.toMainActivity(registerUser);

                    }
                });
            }

            @Override
            public void registerFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                    }
                });
            }
        });
    }

    public void login() {
        userLoginView.showLoading();
        userModel.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //改变界面 需要在UI线程中执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                //改变界面 需要在UI线程中执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

}
