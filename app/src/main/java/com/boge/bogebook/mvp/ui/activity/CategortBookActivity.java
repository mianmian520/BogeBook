package com.boge.bogebook.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.SimpleRecyclerViewAdapter;
import com.boge.bogebook.mvp.view.CategortBookView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategortBookActivity extends BaseActivity implements CategortBookView {

    @Bind(R.id.type_recyclerView)
    RecyclerView typeRecyclerView;
    @Bind(R.id.minor_recyclerView)
    RecyclerView minorRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private String gender;
    private CategoryList.MaleBean maleBean;
    private List<String> datas;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_categort_book;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        initIntent();
        datas = Arrays.asList(getResources().getStringArray(R.array.type_tabs));

        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeRecyclerView.setLayoutManager(linearLayoutManager);
        typeRecyclerView.setHasFixedSize(true);
        SimpleRecyclerViewAdapter adapter = new SimpleRecyclerViewAdapter(datas);
        typeRecyclerView.setAdapter(adapter);
    }

    private void initIntent() {
        maleBean = getIntent().getParcelableExtra("maleBean");
        toolbar.setTitle(maleBean.getName());
        gender = getIntent().getStringExtra(Constant.GENDER);
    }


    @Override
    public void setCategoryListLv2(CategoryListLv2 categoryListLv2) {

    }

    @Override
    public void setBooksByCats(BooksByCats booksByCats) {

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
