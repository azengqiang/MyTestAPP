package net.csdn.my.mineAty;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.activity.BaseActivity;

public class DataSubActivity extends BaseActivity {
    View layoutHead = null;
    View layoutNicknane = null;
    View layoutContactWay = null;
    View layoutLoginPassword = null;
    View layoutReceiverAddress = null;
    boolean headFlag = true;
    boolean nicknaneFlag = true;
    boolean contactWayFlag = true;
    boolean loginPasswordFlag = true;
    boolean receiverAddressFlag = true;
    String dataSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.main_mine_data_head);
        /**
         * 获取上一个intent传递的值
         */
        dataSub = getIntent().getStringExtra("dataSub");
        inflater = this.getLayoutInflater();
        /**
         * 获取子布局的布局
         */
        layoutHead = inflater.inflate(R.layout.main_mine_data_head, null);
        layoutNicknane = inflater.inflate(R.layout.main_mine_data_nickname, null);
        initdata();
    }

    /**
     * 根据前一个Intent传过来的值判断加载哪一个布局
     */
    public void initdata() {
        if (dataSub.equals(HEAD)) {
            jump2Head();
        } else if (dataSub.equals(NICKNAME)) {
            jump2Nickname();
        } else if (dataSub.equals(CONTACTWAY)) {
            jump2ContactWay();
        } else if (dataSub.equals(LOGINPASSWORD)) {
            jump2LoginPassword();
        } else if (dataSub.equals(RECEIVERADDRESS)) {
            jump2ReceiverAddress();
        } else {

        }
    }

    /**
     * 加载头像布局
     */
    public void jump2Head() {
        setContentView(layoutHead);
    }

    /**
     * 加载昵称布局
     */
    public void jump2Nickname() {
        setContentView(layoutNicknane);
    }

    /**
     * 加载联系人布局
     */
    public void jump2ContactWay() {
        setContentView(layoutContactWay);
    }

    /**
     * 加载密码布局
     */
    public void jump2LoginPassword() {
        setContentView(layoutLoginPassword);
    }

    /**
     * 加载收货地址布局
     */
    public void jump2ReceiverAddress() {
        setContentView(layoutReceiverAddress);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
