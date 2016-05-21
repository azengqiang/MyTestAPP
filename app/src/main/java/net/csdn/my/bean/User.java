package net.csdn.my.bean;

/**
 * 用户登陆bean
 * Created by neijiang on 2016/5/10.
 */
public class User {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
    private String password;
}
