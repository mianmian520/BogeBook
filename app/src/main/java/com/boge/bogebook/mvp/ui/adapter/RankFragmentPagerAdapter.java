package com.boge.bogebook.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public class RankFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> datas;

    public RankFragmentPagerAdapter(FragmentManager fm , List<Fragment> fragments , List<String> datas) {
        super(fm);
        this.fragments = fragments;
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 :fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position);
    }
}
