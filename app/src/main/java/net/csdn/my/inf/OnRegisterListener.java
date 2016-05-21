package net.csdn.my.inf;

import net.csdn.my.bean.RegisterUser;

/**
 * Created by lenovo on 2016/5/11.
 */
public interface OnRegisterListener {
    void registerSuccess(RegisterUser registerUser);
    void registerFailed();
}
