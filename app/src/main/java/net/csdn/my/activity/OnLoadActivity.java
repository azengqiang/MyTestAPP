package net.csdn.my.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.inf.OnViewChangeListener;
import net.csdn.my.util.MyScrollLayout;


public class OnLoadActivity extends AppCompatActivity implements OnViewChangeListener {

    private MyScrollLayout mScrollLayout;//自定义的滑动布局
    private ImageView[] imgs;
    private LinearLayout pointLayout;//小圆点布局
    private RelativeLayout onloadLayout;//引导页布局
    private Button mbtn;
    private int count;//当前引导页的数量
    private int currentItem;//当前引导页页数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.onload);
        initView();
    }

    public void initView() {
        mScrollLayout = (MyScrollLayout) findViewById(R.id.msl_onload_page);
        pointLayout = (LinearLayout) findViewById(R.id.ll_onload_point);
        count = mScrollLayout.getChildCount();
        imgs = new ImageView[count];
        for (int i = 0; i < count; i++) {
            imgs[i] = (ImageView) pointLayout.getChildAt(i);
            imgs[i].setEnabled(true);
            imgs[i].setTag(i);
        }
        currentItem = 0;
        imgs[currentItem].setEnabled(false);
        mScrollLayout.SetOnViewChangeListener(this);
    }

    @Override
    public void OnViewChange(int pos) {
        // TODO Auto-generated method stub
        setCurrentPoint(pos);
    }

    //正式进入应用界面
    public void startApp(View view) {
        switch (view.getId()) {
            case R.id.btn_onload_toApp:
                Intent intent = new Intent(OnLoadActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    //设置当前引导页的小圆点的状态
    public void setCurrentPoint(int pos) {
        if (pos < 0 || pos > count - 1 || currentItem == pos) {
            return;
        }
        imgs[currentItem].setEnabled(true);
        imgs[pos].setEnabled(false);
        currentItem = pos;
    }

}
