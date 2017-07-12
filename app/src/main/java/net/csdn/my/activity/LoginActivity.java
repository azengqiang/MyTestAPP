package net.csdn.my.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.view.UserLoginView;
import net.csdn.my.view.UserRegisterView;

/**
 * 登陆界面
 */
public class LoginActivity extends BaseActivity {
    private Button btn_login, btn_register;
    private TextView tv_justlook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tologin);
        initView();
    }

    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_toLogin);
        btn_register = (Button) findViewById(R.id.btn_toRegister);
        tv_justlook = (TextView) findViewById(R.id.tv_tologin_justlook);
        tv_justlook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainInterfaceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserLoginView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserRegisterView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

}
