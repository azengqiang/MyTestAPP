package net.csdn.my.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.bean.MainFanBean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by lenovo on 2016/5/19.
 */
public class FanListAdapter extends BaseAdapter {
    /**
     * 接收数据的list，包含一个数据bean
     */
    private List<MainFanBean> list = new ArrayList<MainFanBean>();
    /**
     * 图片缓存
     * key：缓存的key是该图片的url（唯一）
     * value：要缓存的图片
     */
    private LruCache<String, BitmapDrawable> mImgCache;
    private ListView listView;

    /**
     * fan列表适配器
     * @param list mainfanBean的集合
     */
    public FanListAdapter(List<MainFanBean> list) {
        super();
        this.list = list;
        //获取可以使用的最大缓存
        int maxCache = (int) Runtime.getRuntime().maxMemory();
        //缓存参数传入可用缓存的1/8
        int cacheSize = maxCache / 8;
        //重写sizeof方法，返回图片的大小，因为我们需要计算图片大小判断当前已经使用的缓存
        mImgCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                return value.getBitmap().getByteCount();
            }
        };
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == listView) {
            listView = (ListView) parent;
        }
        View view = convertView;
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.main_fan_listview_item, null);
            holder = new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.iv_main_fan_lv_item);
            holder.title = (TextView) view.findViewById(R.id.tv_main_fan_lv_item_title);
            holder.summy = (TextView) view.findViewById(R.id.tv_main_fan_lv_item_summy);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MainFanBean fan = list.get(position);
        holder.title.setText(fan.getTitle());
        holder.summy.setText(fan.getSummy());
        holder.img.setTag(fan.getImgUrl());
        /**
         * 如果图片本地有缓存，就使用本地的
         */
        if (mImgCache.get(fan.getImgUrl()) != null) {
            holder.img.setImageDrawable(mImgCache.get(fan.getImgUrl()));
        } else {
            /**
             * 如果没有本地图片缓存
             * 开启一个异步任务，加载图片
             */
            ImageTask task = new ImageTask();
            task.execute(fan.getImgUrl());
        }
        return view;
    }

    static class ViewHolder {
        private ImageView img;
        private TextView title, summy;
    }

    /**
     * 图片异步加载任务类
     */
    class ImageTask extends AsyncTask<String, Void, BitmapDrawable> {
        private String imgUrl;

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            imgUrl = params[0];
            Bitmap bitmap = downLoadImg();
            BitmapDrawable db = new BitmapDrawable(listView.getResources(),
                    bitmap);
            if(null==mImgCache.get(imgUrl)){
                mImgCache.put(imgUrl,db);
            }
            return db;
        }

        /**
         * http下载步骤
         * 1.通过URL获取传入的路径
         * URL url = new URL(imgUrl)
         * 2.通过url连接HttpsURLConnection
         * con =(HttpsURLConnection) url.openConnection()
         * 3.设置一些con的参数
         * con.setConnectTimeout(5000);
         * con.setReadTimeout(10000);
         * 4.获取con的输入流，通过BitmapFactory将输入流转换成bitmap
         * bitmap = BitmapFactory.decodeStream(con.getInputStream());
         *
         * @return
         */
        private Bitmap downLoadImg() {
            HttpsURLConnection con = null;
            Bitmap bitmap = null;
            try {
                URL url = new URL(imgUrl);
                con = (HttpsURLConnection) url.openConnection();
                con.setConnectTimeout(5000);
                con.setReadTimeout(10000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                    con.disconnect();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(BitmapDrawable bitmapDrawable) {
            ImageView iv = (ImageView) listView.findViewWithTag(imgUrl);
            if (iv != null & bitmapDrawable != null) {
                iv.setImageDrawable(bitmapDrawable);
            }
        }
    }
}
