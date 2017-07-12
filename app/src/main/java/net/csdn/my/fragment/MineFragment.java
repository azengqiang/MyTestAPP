package net.csdn.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.mineAty.DataActivity;


public class MineFragment extends Fragment implements View.OnClickListener {
    TableRow tr_main_mine_data, tr_main_mine_bound, tr_main_mine_setting, tr_main_mine_car;
    TableRow tr_main_mine_order, tr_main_mine_integral, tr_main_mine_collection, tr_main_mine_choose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_mine, container, false);
        initData(view);
        return view;
    }

    /**
     * 初始化控件并且为控件设置监听器
     *
     * @param view fragment加载的布局
     */
    public void initData(View view) {
        tr_main_mine_data = (TableRow) view.findViewById(R.id.tr_main_mine_data);
        tr_main_mine_order = (TableRow) view.findViewById(R.id.tr_main_mine_order);
        tr_main_mine_car = (TableRow) view.findViewById(R.id.tr_main_mine_car);
        tr_main_mine_collection = (TableRow) view.findViewById(R.id.tr_main_mine_collection);
        tr_main_mine_bound = (TableRow) view.findViewById(R.id.tr_main_mine_bound);
        tr_main_mine_integral = (TableRow) view.findViewById(R.id.tr_main_mine_integral);
        tr_main_mine_choose = (TableRow) view.findViewById(R.id.tr_main_mine_choose);
        tr_main_mine_setting = (TableRow) view.findViewById(R.id.tr_main_mine_setting);

        tr_main_mine_data.setOnClickListener(this);
        tr_main_mine_order.setOnClickListener(this);
        tr_main_mine_car.setOnClickListener(this);
        tr_main_mine_collection.setOnClickListener(this);
        tr_main_mine_bound.setOnClickListener(this);
        tr_main_mine_integral.setOnClickListener(this);
        tr_main_mine_choose.setOnClickListener(this);
        tr_main_mine_setting.setOnClickListener(this);
    }

    /**
     * 点击事件的处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tr_main_mine_data:
                Intent data = new Intent(this.getActivity(), DataActivity.class);
                startActivity(data);
                break;
            case R.id.tr_main_mine_order:
                Intent order = new Intent(this.getActivity(), DataActivity.class);
                startActivity(order);
                break;
            case R.id.tr_main_mine_car:
                Intent car = new Intent(this.getActivity(), DataActivity.class);
                startActivity(car);
                break;
            case R.id.tr_main_mine_collection:
                Intent collection = new Intent(this.getActivity(), DataActivity.class);
                startActivity(collection);
                break;
            case R.id.tr_main_mine_bound:
                Intent bound = new Intent(this.getActivity(), DataActivity.class);
                startActivity(bound);
                break;
            case R.id.tr_main_mine_integral:
                Intent integral = new Intent(this.getActivity(), DataActivity.class);
                startActivity(integral);
                break;
            case R.id.tr_main_mine_choose:
                Intent choose = new Intent(this.getActivity(), DataActivity.class);
                startActivity(choose);
                break;
            case R.id.tr_main_mine_setting:
                Intent setting = new Intent(this.getActivity(), DataActivity.class);
                startActivity(setting);
                break;
            default:
                break;
        }
    }
}
