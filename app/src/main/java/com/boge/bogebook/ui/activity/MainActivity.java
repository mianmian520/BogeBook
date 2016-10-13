package com.boge.bogebook.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.boge.bogebook.R;
import com.boge.bogebook.ui.activity.base.BaseActivity;
import com.boge.bogebook.ui.fragments.BlankFragment;
import com.boge.bogebook.ui.fragments.BookFragment;
import com.boge.bogebook.view.Indicator;

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
        fragments.add(BookFragment.newInstance("male"));
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
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
}
