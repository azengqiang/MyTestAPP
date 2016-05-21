package net.csdn.my.inf;

import android.view.View;

/**
 * 列表布局的子选项点击监听接口
 * Created by neijiang on 2016/5/15.
 */
public interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view , int position);
}
