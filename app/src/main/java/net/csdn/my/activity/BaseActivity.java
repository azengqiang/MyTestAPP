package net.csdn.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    private int mFinishCount;//用户点击退出键的次数
    public static final String HEAD = "HEAD";
    public static final String NICKNAME = "NICKNAME";
    public static final String CONTACTWAY = "CONTACTWAY";
    public static final String LOGINPASSWORD = "LOGINPASSWORD";
    public static final String RECEIVERADDRESS = "RECEIVERADDRESS";
    public static LayoutInflater inflater;
    /*
     dispatchTouchEvent作用是将touch事件向下传递直到遇到被触发的目标view,如果返回true,
     表示当前view就是目标view,事件停止向下分发。否则返回false,表示当前view不是目标view,
     需要继续向下分发寻找目标view.这个方法也可以被重载，手动分配事件。
      */
//    public boolean dispatchTouchEvent(MotionEvent me) {
//        mFinishCount = 0;
//        return super.dispatchTouchEvent(me);
//    }

    private long exitTime = 0;

    /**
     * 监听返回键 通过与系统时间对比
     * 如果两次点击在2000ms内就退出应用
     *
     * @param keyCode 按键是否为返回键
     * @param event   按下动作
     * @return 是否继续传递事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(BaseActivity.this, "确认退出吗", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    //重写finish方法，当用户点击两下退出键，就结束app
//    public void finish() {
//        mFinishCount++;
//        if (1 == mFinishCount) {
//            Toast.makeText(BaseActivity.this, "确认退出吗", Toast.LENGTH_SHORT).show();
//        } else if (2 == mFinishCount) {
//            super.finish();
//        }
//    }

}
