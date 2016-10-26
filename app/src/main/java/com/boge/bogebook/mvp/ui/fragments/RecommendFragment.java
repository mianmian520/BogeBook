package com.boge.bogebook.mvp.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.mvp.presenter.impl.RecommendPresenterImpl;
import com.boge.bogebook.mvp.ui.adapter.RecommendAdapter;
import com.boge.bogebook.mvp.ui.fragments.base.BaseFragment;
import com.boge.bogebook.mvp.view.RecommendView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class RecommendFragment extends BaseFragment implements RecommendView
        ,OnRecyclerViewItemClick,SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private RecommendAdapter adapter;

    private String gender;

    @Inject
    RecommendPresenterImpl recommendPresenter;

    public static RecommendFragment newInstance(String param1) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(Constant.GENDER, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gender = getArguments().getString(Constant.GENDER);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initDatas() {
        recommendPresenter.attachView(this);
        recommendPresenter.loadRecommendBook(gender);

        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new RecommendAdapter();
        adapter.setOnRecyclerViewItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化SwipeRefreshLayout
     */
    private void initSwipeRefreshLayout() {
        //下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色,一种颜色转一圈 循环
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipeRefreshLayout.setDistanceToTriggerSync(400);
        //进度圈的背景色
        swipeRefreshLayout.setProgressBackgroundColor(android.R.color.white);
        //设置进度圈的大小，只有两个值：DEFAULT、LARGE
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void setReCommendBook(List<Recommend.RecommendBook> recommendBookList) {
        if(recommendBookList != null){//推荐的是否为空
            for (Recommend.RecommendBook recommed : recommendBookList) {
                //第一次加载推荐
                if(adapter.getRecommendBooks().size() == 0){
                    recommed.setHasUp(true);
                } else {
                    for(Recommend.RecommendBook recommendBook : adapter.getRecommendBooks()) {
                        if (recommed.get_id().equals(recommendBook.get_id())) {
                            if (!recommed.getLastChapter().equals(recommendBook.getLastChapter())) {
                                recommed.setHasUp(true);
                            } else {
                                recommed.setHasUp(recommendBook.isHasUp());
                            }
                            break;
                        }
                    }
                }
            }
            adapter.setRecommendBooks(recommendBookList);
        }
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showProgress() {
        if(!swipeRefreshLayout.isRefreshing()){
            progressBar.setVisibility(View.VISIBLE);
        }
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
    public void onItemClick(View v, int position) {
        Snackbar.make(recyclerView , adapter.getRecommendBooks().get(position).toString() , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        recommendPresenter.loadRecommendBook(gender);
    }
}
