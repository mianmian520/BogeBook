package com.boge.bogebook.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.boge.bogebook.R;
import com.boge.bogebook.ui.fragments.BlankFragment;
import com.boge.bogebook.view.Indicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.indicator)
    Indicator indicator;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    List<Fragment> fragments;
    List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        datas = Arrays.asList(getResources().getStringArray(R.array.home_tabs));
        indicator.setTabItemTitles(datas);

        fragments = new ArrayList<Fragment>();
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        indicator.setViewPager(viewPager , 0);
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
