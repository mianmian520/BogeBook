package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.fragments.BlankFragment;
import com.boge.bogebook.mvp.ui.fragments.FindFragment;
import com.boge.bogebook.mvp.ui.fragments.RecommendFragment;
import com.boge.bogebook.view.Indicator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.indicator)
    Indicator indicator;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    List<Fragment> fragments;
    List<String> datas;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        datas = Arrays.asList(getResources().getStringArray(R.array.home_tabs));
        indicator.setTabItemTitles(datas);

        fragments = new ArrayList<Fragment>();
        fragments.add(RecommendFragment.newInstance(Constant.MALE));
        fragments.add(new BlankFragment());
        fragments.add(new FindFragment());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        indicator.setViewPager(viewPager , 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }

    /**
     * 菜单拦
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 显示menu的图标
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search://搜索
                break;
            case R.id.action_login://登录
                break;
            case R.id.action_my_message://消息
                break;
            case R.id.action_synchronous://同步
                break;
            case R.id.action_local_book://扫描本地书籍
                startActivity(new Intent(this , LocalBookActivity.class));
                break;
            case R.id.action_wifi_upload://WIFI传书
                break;
            case R.id.action_feedback://一件反馈
                break;
            case R.id.action_night://夜间模式
                break;
            case R.id.action_setting://设置
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
