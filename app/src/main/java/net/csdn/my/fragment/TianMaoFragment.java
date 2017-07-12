package net.csdn.my.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.bean.TianMaoItem02;
import net.csdn.my.util.DividerItemDecoration;
import net.csdn.my.util.RecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TianMaoFragment extends Fragment {
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;
    Integer[] img = {R.drawable.w3, R.drawable.w4, R.drawable.w5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tian_mao, container, false);
        initData(view);
        return view;
    }

    /**
     * 初始化数据
     * @param view
     */
    public void initData(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_main_tian_mao);
        /**
         * 初始化，将数据传入recyclerView适配器
         */

        recycleViewAdapter = new RecycleViewAdapter(this.getActivity(), getItem01Data(), getItem02Data());
        /**
         * 布局管理器，可以设置布局显示的样式，这里选择的是线性布局
         */
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        /**
         * 设置item之间的分隔线
         */
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        /**
         *
         * 设置item的增删动画
         */
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /**
         * 设置recyclerView适配器
         */
        recyclerView.setAdapter(recycleViewAdapter);
    }


    public List<Integer> getItem01Data() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(img[0]);
        list.add(img[1]);
        list.add(img[2]);
        list.add(img[0]);
        list.add(img[1]);
        list.add(img[2]);
        list.add(img[0]);
        list.add(img[1]);
        list.add(img[2]);
        list.add(img[2]);
        return list;
    }

    public List<TianMaoItem02> getItem02Data() {
        List<TianMaoItem02> list = new ArrayList<TianMaoItem02>();
        TianMaoItem02 item = new TianMaoItem02();
        for (int i = 0; i <10; i++) {
            item.setTitle("我是标题" + i);
            item.setContent("我是标题" + i + "的内容");
            item.setImgUrl(img[i%3]);
            list.add(item);
        }
        return list;
    }
}
