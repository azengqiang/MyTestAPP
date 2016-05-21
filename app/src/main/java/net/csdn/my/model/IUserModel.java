package net.csdn.my.model;

import net.csdn.my.inf.OnLoginListener;
import net.csdn.my.inf.OnRegisterListener;

/**
 * 处理用户的业务逻辑和实体模型的接口
 * Created by neijiang on 2016/5/10.
 */
public interface IUserModel {
    public void login(String userName, String password, OnLoginListener loginListener);
    public void register(String userName, String password, OnRegisterListener registerListener);
}
