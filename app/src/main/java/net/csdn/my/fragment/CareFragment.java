package net.csdn.my.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.inf.OnViewChangeListener;
import net.csdn.my.util.MyScrollLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CareFragment extends Fragment implements OnViewChangeListener {
    private GridView gv;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private String TAG="CareFragment";
    // 图片封装为一个数组
    private int[] icon = {R.drawable.tab01, R.drawable.tab02, R.drawable.tab03,
            R.drawable.tab04, R.drawable.tab05, R.drawable.tab06,
            R.drawable.tab07, R.drawable.tab08};
    private String[] iconName = {"积分乐园", "聚划算", "充值", "分类", "天猫国际", "天猫超市", "红包", "试用"};

    private MyScrollLayout mScrollLayout;//自定义的滑动布局
    private ImageView[] imgs;
    private LinearLayout pointLayout;//小圆点布局
    private RelativeLayout care_topLayout;//care上方滑动页布局
    private int count;//当前滑动页的数量
    private int currentItem;//当前滑动页页数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_care, container, false);
        initView(view);

        return view;
    }

    public void initView(View view) {
        gv = (GridView) view.findViewById(R.id.gv_main_care);
        mScrollLayout = (MyScrollLayout) view.findViewById(R.id.msl_care_top_page);
        pointLayout = (LinearLayout) view.findViewById(R.id.ll_care_top_point);
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
    }

    @Override
    public void OnViewChange(int pos) {
        // TODO Auto-generated method stub
        setCurrentPoint(pos);
    }

    //设置当前滑动页的小圆点的状态
    public void setCurrentPoint(int pos) {
        if (pos < 0 || pos > count - 1 || currentItem == pos) {
            return;
        }
        imgs[currentItem].setEnabled(true);
        imgs[pos].setEnabled(false);
        currentItem = pos;
    }
    /**
     * 获取gridview布局的数据
     *
     * @return
     */
    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        Log.d(TAG,"grid长度"+icon.length);

        return data_list;
    }
}
