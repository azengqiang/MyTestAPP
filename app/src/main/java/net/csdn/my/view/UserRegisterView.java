package net.csdn.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.Presenter.UserPresenter;
import net.csdn.my.activity.LoginActivity;
import net.csdn.my.bean.RegisterUser;

/**
 * Created by lenovo on 2016/5/11.
 */
public class UserRegisterView extends AppCompatActivity implements IUserRegisterView {
    private EditText et_username, et_password;
    private Button btn_register;
    private UserPresenter userPresenter = new UserPresenter(this, this);

    /**
     * 用户注册的主持者，负责登陆view和登陆model的交互
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        initView();
    }

    /**
     * 完成一些初始化工作，加载控件，调用点击事件等
     */
    public void initView() {
        //获取注册的用户名、密码
        et_username = (EditText) findViewById(R.id.et_register_username);
        et_password = (EditText) findViewById(R.id.et_register_password);
        //注册按钮
        btn_register = (Button) findViewById(R.id.btn_register);
        //注册按钮,调用UserPresenter的登陆功能
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.register();
            }
        });
    }

    public String getUserName() {
        return et_username.getText().toString();
    }


    public String getPassword() {
        return et_password.getText().toString();
    }

    public void toMainActivity(RegisterUser registerUser) {
        Toast.makeText(UserRegisterView.this, R.string.registerMsg_success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UserRegisterView.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showFailedError() {
        Toast.makeText(UserRegisterView.this, R.string.registerMsg_failed, Toast.LENGTH_SHORT).show();
    }
}
