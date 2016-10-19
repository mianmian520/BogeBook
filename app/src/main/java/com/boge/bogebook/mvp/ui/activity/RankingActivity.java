package com.boge.bogebook.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.mvp.presenter.impl.RankingListPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.RankingTopAdapter;
import com.boge.bogebook.mvp.view.RankingView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static com.boge.bogebook.R.id.recyclerView;

public class RankingActivity extends BaseActivity implements RankingView {


    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.maleExpandableListView)
    ExpandableListView maleExpandableListView;
    @Bind(R.id.femaleExpandableListView)
    ExpandableListView femaleExpandableListView;

    @Inject
    RankingListPresenterImpl rankingListPresenter;

    private List<RankingList.MaleBean> maleGroups = new ArrayList<>();
    private List<List<RankingList.MaleBean>> maleChilds = new ArrayList<>();
    private RankingTopAdapter maleAdapter;

    private List<RankingList.MaleBean> femaleGroups = new ArrayList<>();
    private List<List<RankingList.MaleBean>> femaleChilds = new ArrayList<>();
    private RankingTopAdapter femaleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ranking;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        rankingListPresenter.attachView(this);
        rankingListPresenter.loadRankingList();

        maleAdapter = new RankingTopAdapter(this, maleGroups, maleChilds);
        femaleAdapter = new RankingTopAdapter(this, femaleGroups, femaleChilds);

        maleExpandableListView.setAdapter(maleAdapter);
        femaleExpandableListView.setAdapter(femaleAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setRankingList(RankingList rankingList) {
        if (rankingList != null) {
            maleGroups.clear();
            femaleGroups.clear();
            Log.i("test" , rankingList.toString());
            updateMale(rankingList);
            updateFemale(rankingList);
        }
    }

    private void updateMale(RankingList rankingList) {
        List<RankingList.MaleBean> male = rankingList.getMale();
        List<RankingList.MaleBean> collapse = new ArrayList<>();
        for (RankingList.MaleBean bean : male){
            if(bean.isCollapse()){
                collapse.add(bean);
            } else {
                maleGroups.add(bean);
                maleChilds.add(new ArrayList<RankingList.MaleBean>());
            }
        }
        if (collapse.size() > 0) {
            maleGroups.add(new RankingList.MaleBean("别人家的排行榜"));
            maleChilds.add(collapse);
        }
        maleAdapter.notifyDataSetChanged();
    }

    private void updateFemale(RankingList rankingList) {
        List<RankingList.MaleBean> list = rankingList.getFemale();
        List<RankingList.MaleBean> collapse = new ArrayList<>();
        for (RankingList.MaleBean bean : list) {
            if (bean.isCollapse()) { // 折叠
                collapse.add(bean);
            } else {
                femaleGroups.add(bean);
                femaleChilds.add(new ArrayList<RankingList.MaleBean>());
            }
        }
        if (collapse.size() > 0) {
            femaleGroups.add(new RankingList.MaleBean("别人家的排行榜"));
            femaleChilds.add(collapse);
        }
        femaleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(progressBar, message, Snackbar.LENGTH_LONG).show();
    }
}
