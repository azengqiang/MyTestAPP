package net.csdn.my.mineAty;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TableRow;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.activity.BaseActivity;
import net.csdn.my.activity.MainInterfaceActivity;

public class DataActivity extends BaseActivity implements View.OnClickListener {
    Button ibtn_back;
    TableRow tr_main_mine_data_head, tr_main_mine_data_nickname, tr_main_mine_data_contactWay;
    TableRow tr_main_mine_data_loginPassword, tr_main_mine_data_receiverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_mine_data);
        initData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化监听器并且设置点击事件
     */
    public void initData() {
        ibtn_back = (Button) findViewById(R.id.btn_back);
        tr_main_mine_data_head = (TableRow) findViewById(R.id.tr_main_mine_data_head);
        tr_main_mine_data_nickname = (TableRow) findViewById(R.id.tr_main_mine_data_nickname);
        tr_main_mine_data_contactWay = (TableRow) findViewById(R.id.tr_main_mine_data_contactWay);
        tr_main_mine_data_loginPassword = (TableRow) findViewById(R.id.tr_main_mine_data_loginPassword);
        tr_main_mine_data_receiverAddress = (TableRow) findViewById(R.id.tr_main_mine_data_receiverAddress);


        ibtn_back.setOnClickListener(this);
        tr_main_mine_data_head.setOnClickListener(this);
        tr_main_mine_data_nickname.setOnClickListener(this);
        tr_main_mine_data_contactWay.setOnClickListener(this);
        tr_main_mine_data_loginPassword.setOnClickListener(this);
        tr_main_mine_data_receiverAddress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent back = new Intent(DataActivity.this, MainInterfaceActivity.class);
                startActivity(back);
                break;
            case R.id.tr_main_mine_data_head:
                Intent head = new Intent(DataActivity.this, DataSubActivity.class);
                head.putExtra("dataSub", HEAD);
                startActivity(head);
                break;
            case R.id.tr_main_mine_data_nickname:
                Intent nickname = new Intent(DataActivity.this, DataSubActivity.class);
                nickname.putExtra("dataSub", NICKNAME);
                startActivity(nickname);
                break;
            case R.id.tr_main_mine_data_contactWay:
                Intent contactWay = new Intent(DataActivity.this, DataSubActivity.class);
                contactWay.putExtra("dataSub", CONTACTWAY);
                startActivity(contactWay);
                break;
            case R.id.tr_main_mine_data_loginPassword:
                Intent loginPassword = new Intent(DataActivity.this, DataSubActivity.class);
                loginPassword.putExtra("dataSub", LOGINPASSWORD);
                startActivity(loginPassword);
                break;
            case R.id.tr_main_mine_data_receiverAddress:
                Intent receiverAddress = new Intent(DataActivity.this, DataSubActivity.class);
                receiverAddress.putExtra("dataSub", RECEIVERADDRESS);
                startActivity(receiverAddress);
                break;
            default:
                break;
        }
    }
}
