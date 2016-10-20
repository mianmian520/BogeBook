package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.RankFragmentPagerAdapter;
import com.boge.bogebook.mvp.ui.fragments.RankDetailFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class RankDetailActivity extends BaseActivity {

    @Bind(R.id.container)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    private List<String> datas = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank_detail;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        initIntent();
        RankFragmentPagerAdapter fragmentPagerAdapter = new RankFragmentPagerAdapter(getSupportFragmentManager() , fragments , datas);
        mViewPager.setAdapter(fragmentPagerAdapter);
        tabs.setupWithViewPager(mViewPager);
    }

    private void initIntent() {
        Intent intent = getIntent();
        RankingList.MaleBean maleBean = intent.getParcelableExtra("maleBean");
        if(!maleBean.isCollapse()){
            datas = Arrays.asList(getResources().getStringArray(R.array.rang_tabs));
            fragments.add(RankDetailFragment.newInstance(maleBean.get_id()));
            fragments.add(RankDetailFragment.newInstance(maleBean.getMonthRank()));
            fragments.add(RankDetailFragment.newInstance(maleBean.getTotalRank()));
        } else {
            tabs.setVisibility(View.GONE);datas.add("");
            fragments.add(RankDetailFragment.newInstance(maleBean.get_id()));
        }
        toolbar.setTitle(maleBean.getTitle());
    }
}
