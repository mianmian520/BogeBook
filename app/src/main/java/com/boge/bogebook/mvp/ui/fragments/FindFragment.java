package com.boge.bogebook.mvp.ui.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.support.FindBean;
import com.boge.bogebook.mvp.ui.activity.CategoryActivity;
import com.boge.bogebook.mvp.ui.activity.RankingActivity;
import com.boge.bogebook.mvp.ui.adapter.FindAdapter;
import com.boge.bogebook.mvp.ui.adapter.RecommendAdapter;
import com.boge.bogebook.mvp.ui.fragments.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FindFragment extends BaseFragment implements RecommendAdapter.OnRecyclerViewItemClick{

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<FindBean> findBeanList = new ArrayList<FindBean>();

    private FindAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initDatas() {
        initFindBeanList();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new FindAdapter(findBeanList);
        adapter.setOnRecyclerViewItemClick(this);
        recyclerView.setAdapter(adapter);
    }

    private void initFindBeanList() {
        findBeanList.clear();
        findBeanList.add(new FindBean("排行榜", R.mipmap.home_find_rank));
        findBeanList.add(new FindBean("分类", R.mipmap.home_find_category));
        findBeanList.add(new FindBean("主题书单", R.mipmap.home_find_topic));
        findBeanList.add(new FindBean("有声小说", R.mipmap.home_find_listen));
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (position){
            case 0:
                getActivity().startActivity(new Intent(getActivity() , RankingActivity.class));
                break;
            case 1:
                getActivity().startActivity(new Intent(getActivity() , CategoryActivity.class));
                break;
        }
    }
}
