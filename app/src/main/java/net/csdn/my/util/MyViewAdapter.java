package net.csdn.my.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.bean.TianMaoItem02;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表布局的适配器
 * Created by neijiang on 2016/5/15.
 */
public class MyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context context;
    List<TianMaoItem02> item02_list = new ArrayList<TianMaoItem02>();
    List<Integer> item01_list = new ArrayList<Integer>();

    /**
     * *适配器构造器
     *
     * @param context     上下文
     * @param item02_list 列表数据
     */
    public MyViewAdapter(Context context, List<Integer> item01_list, List<TianMaoItem02> item02_list) {
        this.context = context;
        this.item01_list = item01_list;
        this.item02_list = item02_list;
        mLayoutInflater = LayoutInflater.from(context);

    }

    /**
     * 建立枚举 2个item 类型
     * Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0，
     * ITEM_TYPE.ITEM2.ordinal()代表1
     */
    public enum ITEM_TYPE {
        ITEM1, ITEM2
    }

    /**
     * 根据传入的子布局的类型，创建子布局的UI，返回一个包含子布局所有界面元素的holder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new MyViewHolder01(mLayoutInflater.inflate
                    (R.layout.main_tian_mao_item_01, parent, false));
        } else {
            return new MyViewHolder02(mLayoutInflater.inflate
                    (R.layout.main_tian_mao_item_02, parent, false));
        }
    }

    /**
     * 判断子布局的类型，加载相应布局的数据
     *
     * @param holder   子布局的控件holder
     * @param position 当前绑定的选项的位置
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder01) {
            MyViewHolder01 mHolder01 = (MyViewHolder01) holder;
            mHolder01.iv.setImageResource(item01_list.get(position));

        } else if (holder instanceof MyViewHolder02) {
            MyViewHolder02 mHolder02 = (MyViewHolder02) holder;
            TianMaoItem02 item02 = item02_list.get(position);
            mHolder02.tv_title.setText(item02.getTitle());
            mHolder02.tv_content.setText(item02.getContent());
            mHolder02.iv.setImageResource(item02.getImgUrl());
        }
    }

    /**
     * 设置ITEM类型，这里设置item position单数显示item1 偶数显示item2
     */
    @Override
    public int getItemViewType(int position) {

        return 2 == 2 ? ITEM_TYPE.ITEM2.ordinal() : ITEM_TYPE.ITEM1.ordinal();
    }

    @Override
    public int getItemCount() {
        return item02_list.size();
    }

    /**
     * 列表布局的子选项布局1，里面包含布局中的所有界面元素
     */
    public static class MyViewHolder01 extends RecyclerView.ViewHolder {

        ImageView iv;

        public MyViewHolder01(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv_main_tian_mao_item_01);
        }
    }

    /**
     * 列表布局的子选项布局2，里面包含布局中的所有界面元素
     */
    public static class MyViewHolder02 extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_title, tv_content;

        public MyViewHolder02(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv_main_tian_mao_item_02);
            tv_title = (TextView) view.findViewById(R.id.tv_main_tian_mao_item_02_title);
            tv_content = (TextView) view.findViewById(R.id.tv_main_tian_mao_item_02_content);
        }
    }
}