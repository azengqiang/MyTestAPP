package net.csdn.my.util;

import android.content.Context;
import android.widget.Toast;

/**
 *
 * Created by neijiang on 2016/5/11.
 */
public class MyToast {
    public MyToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
