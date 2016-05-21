package net.csdn.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.Presenter.UserPresenter;
import net.csdn.my.activity.MainInterfaceActivity;
import net.csdn.my.bean.User;


/**
 * 用户登陆的视图,实现用户登陆的视图接口，针对接口编程
 * 主要工作：
 * 1.获取登陆界面中的各种控件，加载一些必要的资源
 * 2.提供给外界改变界面的调用方法
 * 3.绘制界面以及与用户交互
 * Created by neijiang on 2016/5/10.
 */
public class UserLoginView extends AppCompatActivity implements IUserLoginView {
    private EditText et_username, et_password;
    private Button btn_login;
    private TextView tv_forget_password;
    private ProgressBar pb_login;
    /**
     * 用户登陆的主持者，负责登陆view和登陆model的交互
     */
    private UserPresenter mUserPresenter = new UserPresenter(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        initView();
    }

    /**
     * 完成一些初始化工作，加载控件，调用点击事件等
     */
    public void initView() {
        //获取用户名、密码
        et_username = (EditText) findViewById(R.id.et_login_username);
        et_password = (EditText) findViewById(R.id.et_login_password);
        //登陆按钮
        btn_login = (Button) findViewById(R.id.btn_login);
        //忘记密码
        tv_forget_password = (TextView) findViewById(R.id.tv_login_forget_password);
        //登陆进度条
        pb_login = (ProgressBar) findViewById(R.id.pb_login);
        //登陆按钮,调用UserPresenter的登陆功能
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.login();
            }
        });
    }

    public String getUserName() {
        return et_username.getText().toString();
    }


    public String getPassword() {
        return et_password.getText().toString();
    }


    public void showLoading() {
        pb_login.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        pb_login.setVisibility(View.GONE);
    }

    public void toMainActivity(User user) {
        Toast.makeText(this, R.string.loginMsg_success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserLoginView.this, MainInterfaceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void showFailedError() {
        Toast.makeText(this, R.string.loginMsg_failed, Toast.LENGTH_SHORT).show();
    }

}
