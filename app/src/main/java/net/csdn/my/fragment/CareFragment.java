package net.csdn.my.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.lenovo.mytestapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CareFragment extends Fragment {
    private GridView gv;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = {R.drawable.tab01, R.drawable.tab02, R.drawable.tab03,
            R.drawable.tab04, R.drawable.tab05, R.drawable.tab06,
            R.drawable.tab07, R.drawable.tab08};
    private String[] iconName = {"积分乐园", "聚划算", "充值", "分类", "天猫国际", "天猫超市", "红包", "试用"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_care, container, false);
        gv = (GridView) view.findViewById(R.id.gv_main_care);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.iv_main_care_grid_item, R.id.tv_main_care_grid_item};
        sim_adapter = new SimpleAdapter(this.getActivity(), getData(), R.layout.main_care_grid_item, from, to);
        //配置适配器
        gv.setAdapter(sim_adapter);
        return view;
    }

    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }
}
