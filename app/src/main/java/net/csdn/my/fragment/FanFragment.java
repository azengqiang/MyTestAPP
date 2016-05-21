package net.csdn.my.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.bean.MainFanBean;
import net.csdn.my.util.FanListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FanFragment extends Fragment {
    private ListView listView;
    private List<MainFanBean> list;
    private String HTTPURL = "http://litchiapi.jstv" +
            ".com/api/GetFeeds?column=3&PageSize=20&p" +
            "ageIndex=1&val=100511D3BE5301280E0992C73A9DEC41";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fan, container, false);
        listView = (ListView) view.findViewById(R.id.lv_main_fan);
        initData();
        return view;
    }


    /**
     * 初始化数据
     * 使用OKHTTP 获取json数据
     * 并将数据封装成javabean 添加到list<bean>集合中去
     */
    public void initData() {
        list = new ArrayList<MainFanBean>();
        OkHttpClient client = new OkHttpClient();
        /**
         * Request是OkHttp中访问的请求， Builder是辅助类，Response即OkHttp中的响应
         * 请求代码： Request request = new Request.Builder().url(url).build();
         * 响应代码： Response response = client.newCall(request).execute();
         *
         */
        Request request = new Request.Builder().url(HTTPURL).build();
        Call call = client.newCall(request);
        /**
         * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
         */
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    /**
                     * response.body()返回ResponseBody类
                     * 可以方便的获取string
                     */
                    JSONObject jb1 = new JSONObject(response.body().string());
                    JSONObject jb2 = jb1.getJSONObject("paramz");
                    JSONArray ja = jb2.getJSONArray("feeds");
                    MainFanBean mainFanBean = null;
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject data = ja.getJSONObject(i).getJSONObject("data");
                        String imgUrl = "http://litchiapi.jstv.com" + data.getString("cover");
                        String title = data.getString("subject");
                        String summary = data.getString("summary");
                        mainFanBean = new MainFanBean(imgUrl, title, summary);
                        list.add(mainFanBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mHandler.obtainMessage(0).sendToTarget();

            }
        });
    }

    /**
     * handler 根据发送的信息设置listview的适配器
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    FanListAdapter adapter = new FanListAdapter(list);
                    listView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

}
