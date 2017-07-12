package net.csdn.my.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lenovo.mytestapp.R;

import net.csdn.my.fragment.CareFragment;
import net.csdn.my.fragment.FanFragment;
import net.csdn.my.fragment.MineFragment;
import net.csdn.my.fragment.ShopCarFragment;
import net.csdn.my.fragment.TianMaoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainInterfaceActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 主界面的中间布局的viewpager
     */
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    /**
     * tab选项的碎片集合
     */
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    /**
     * tab各个选项的布局
     */
    private LinearLayout main_tian_mao;
    private LinearLayout main_care;
    private LinearLayout main_fan;
    private LinearLayout main_shop_car;
    private LinearLayout main_mine;
    /**
     * tab选项布局中的图片按钮
     */
    private ImageButton ibtn_main_tian_mao;
    private ImageButton ibtn_main_care;
    private ImageButton ibtn_main_fan;
    private ImageButton ibtn_main_shop_car;
    private ImageButton ibtn_main_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initViews();
        initEvents();
        select(0);
    }

    /**
     * tab选项图片按钮点击事件
     */
    private void initEvents() {
        //布局监听事件
        ibtn_main_tian_mao.setOnClickListener(this);
        ibtn_main_care.setOnClickListener(this);
        ibtn_main_fan.setOnClickListener(this);
        ibtn_main_shop_car.setOnClickListener(this);
        ibtn_main_mine.setOnClickListener(this);
    }

    /**
     * 控件初始化
     */
    public void initViews() {
        /**
         * 主界面中间的viewpager布局
         */
        mViewPager = (ViewPager) findViewById(R.id.vp_main_mid);
        /**
         *  tab选项
         */
        main_tian_mao = (LinearLayout) findViewById(R.id.ll_main_tian_mao);
        main_care = (LinearLayout) findViewById(R.id.ll_main_care);
        main_fan = (LinearLayout) findViewById(R.id.ll_main_fan);
        main_shop_car = (LinearLayout) findViewById(R.id.ll_main_shop_car);
        main_mine = (LinearLayout) findViewById(R.id.ll_main_mine);
        /**
         * tab选项对应的图片按钮
         */
        ibtn_main_tian_mao = (ImageButton) findViewById(R.id.ibtn_main_tian_mao);
        ibtn_main_care = (ImageButton) findViewById(R.id.ibtn_main_care);
        ibtn_main_fan = (ImageButton) findViewById(R.id.ibtn_main_fan);
        ibtn_main_shop_car = (ImageButton) findViewById(R.id.ibtn_main_shop_car);
        ibtn_main_mine = (ImageButton) findViewById(R.id.ibtn_main_mine);
        /**
         * 创建tab选项对应的布局碎片
         */
        Fragment fragment_tian_mao = new TianMaoFragment();
        Fragment fragment_care = new CareFragment();
        Fragment fragment_fan = new FanFragment();
        Fragment fragment_shop_car = new ShopCarFragment();
        Fragment fragment_mine = new MineFragment();
        /**
         * 将tab碎片添加进list集合
         */
        mFragments.add(fragment_tian_mao);
        mFragments.add(fragment_care);
        mFragments.add(fragment_fan);
        mFragments.add(fragment_shop_car);
        mFragments.add(fragment_mine);
        /**
         * 碎片页面适配器
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                // TODO Auto-generated method stub
                return mFragments.get(position);
            }
        };
        /**
         * 设置适配器
         */
        mViewPager.setAdapter(mAdapter);
        /**
         * viewpager滑动响应事件
         */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 选中卡片 设置为当前viewpager
     */
    public void select(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    /**
     * 设置选中的tab为高亮
     *
     * @param i
     */
    private void setTab(int i) {
        resetImg();
        switch (i) {
            case 0:
                ibtn_main_tian_mao.setImageResource(R.drawable.main_tian_mao_pressed);
                break;
            case 1:
                ibtn_main_care.setImageResource(R.drawable.main_care_pressed);
                break;
            case 2:
                ibtn_main_fan.setImageResource(R.drawable.main_fan_pressed);
                break;
            case 3:
                ibtn_main_shop_car.setImageResource(R.drawable.main_shop_car_pressed);
                break;
            case 4:
                ibtn_main_mine.setImageResource(R.drawable.main_mine_pressed);
                break;
            default:
                break;
        }
    }

    /**
     * tab图片按钮点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ibtn_main_tian_mao:
                select(0);
                break;
            case R.id.ibtn_main_care:
                select(1);
                break;
            case R.id.ibtn_main_fan:
                select(2);
                break;
            case R.id.ibtn_main_shop_car:
                select(3);
                break;
            case R.id.ibtn_main_mine:
                select(4);
                break;
            default:
                break;
        }
    }

    /**
     * tab选项图片按钮未被选中的状态
     */
    private void resetImg() {
        ibtn_main_tian_mao.setImageResource(R.drawable.main_tian_mao_normal);
        ibtn_main_care.setImageResource(R.drawable.main_care_normal);
        ibtn_main_fan.setImageResource(R.drawable.main_fan_normal);
        ibtn_main_shop_car.setImageResource(R.drawable.main_shop_car_normal);
        ibtn_main_mine.setImageResource(R.drawable.main_mine_normal);
    }


}
