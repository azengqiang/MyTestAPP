package net.csdn.my.model;

import android.content.Context;

import net.csdn.my.bean.RegisterUser;
import net.csdn.my.bean.User;
import net.csdn.my.db.UserService;
import net.csdn.my.inf.OnLoginListener;
import net.csdn.my.inf.OnRegisterListener;

/**
 * 处理用户的业务逻辑和实体模型的接口的实现类
 * 功能：
 * 1.负责登陆的业务判断
 * 2.负责注册的业务判断
 * Created by neijiang on 2016/5/10.
 */
public class UserModel implements IUserModel {
    private Context context;
    private UserService userService;
    public UserModel() {
    }

    public UserModel(Context context) {
        this.context = context;
    }

    public void login(final String userName, final String password, final OnLoginListener loginListener) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userService = new UserService(context);
        if (userService.login(user)) {
            loginListener.loginSuccess(user);
        } else {
            loginListener.loginFailed();
        }
    }

    @Override
    public void register(final String userName, final String password, final OnRegisterListener registerListener) {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setUserName(userName);
        registerUser.setPassword(password);
        userService = new UserService(context);
        if(userService.register(registerUser)){
            registerListener.registerSuccess(registerUser);
        }else{
            registerListener.registerFailed();
        }
    }
}
