package com.boge.bogebook.mvp.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.mvp.ui.fragments.base.BaseFragment;
import com.boge.bogebook.mvp.view.RankDetailView;

import butterknife.Bind;

public class RankDetailFragment extends BaseFragment implements RankDetailView{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private String rankingId;

    public static RankDetailFragment newInstance(String param1) {
        RankDetailFragment fragment = new RankDetailFragment();
        Bundle args = new Bundle();
        args.putString(Constant.RANKINGID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rankingId = getArguments().getString(Constant.RANKINGID);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_rank_detail;
    }

    @Override
    protected void initDatas() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
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
        Snackbar.make(recyclerView , message , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRankings(Rankings rankings) {

    }
}
