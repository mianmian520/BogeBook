package com.boge.bogebook.mvp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.mvp.presenter.impl.RankDetailPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.BookDetailActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListDetailAdapter;
import com.boge.bogebook.mvp.ui.fragments.base.BaseFragment;
import com.boge.bogebook.mvp.view.RankDetailView;

import javax.inject.Inject;

import butterknife.Bind;

public class RankDetailFragment extends BaseFragment implements RankDetailView
                ,BookListDetailAdapter.OnRecyclerViewItemClick{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    /***所要查找的排行榜id*/
    private String rankingId;

    @Inject
    RankDetailPresenterImpl rankDetailPresenter;

    /***书籍详细列表适配器*/
    private BookListDetailAdapter adapter;

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
        rankDetailPresenter.attachView(this);
        rankDetailPresenter.loadRankDetail(rankingId);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookListDetailAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnRecyclerViewItemClick(this);
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
        if(rankings != null){
            adapter.setBooksBeen(rankings.getRanking().getBooks());
        }
    }

    @Override
    public void onItemClick(View v, int position) {
//        Log.i("test" , adapter.getBooksBeen().get(position).toString());
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra("bookId" , adapter.getBooksBeen().get(position).get_id());
        startActivity(intent);
    }
}
