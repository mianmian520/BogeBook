package com.boge.bogebook.mvp.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.mvp.presenter.impl.ThemeBookPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.SimpleRecyclerViewAdapter;
import com.boge.bogebook.mvp.view.ThemeBookView;
import com.boge.bogebook.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class ThemeBookActivity extends BaseActivity implements ThemeBookView{

    @Bind(R.id.type_recyclerView)
    RecyclerView typeRecyclerView;
    @Bind(R.id.tag_recyclerView)
    RecyclerView tagRecyclerView;
    @Bind(R.id.iv_up_down)
    ImageView ivUpDown;
    @Bind(R.id.ll_screening)
    LinearLayout llScreening;
    @Bind(R.id.book_recyclerView)
    RecyclerView bookRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private List<String> datas;
    private List<String> tags;

    private SimpleRecyclerViewAdapter typeAdapter;
    private SimpleRecyclerViewAdapter tagAdapter;

    @Inject
    ThemeBookPresenterImpl themeBookPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_book;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        initTypeRecyclerView();
        initTagRecyclerView();

        basePresenter = themeBookPresenter;
        themeBookPresenter.attachView(this);
        themeBookPresenter.loadTagType();
    }

    private void initTagRecyclerView() {
        String s = SharedPreferencesUtil.getInstance().getString(Constant.BOOK_TAG);
        tags = new Gson().fromJson(s , new TypeToken<ArrayList<String>>(){}.getType());
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tagRecyclerView.setLayoutManager(linearLayoutManager);
        tagRecyclerView.setHasFixedSize(true);
        tagAdapter = new SimpleRecyclerViewAdapter(tags);
        tagRecyclerView.setAdapter(tagAdapter);
    }

    /**
     * 类型RecyclerView （本周最热，最新发布,最多收藏）
     */
    private void initTypeRecyclerView() {
        datas = Arrays.asList(getResources().getStringArray(R.array.duration_tabs));
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeRecyclerView.setLayoutManager(linearLayoutManager);
        typeRecyclerView.setHasFixedSize(true);
        typeAdapter = new SimpleRecyclerViewAdapter(datas);
        typeRecyclerView.setAdapter(typeAdapter);
    }

    @Override
    public void setTagsType(List<BookListTags.DataBean> dataBeanList) {
        for (BookListTags.DataBean dataBean : dataBeanList){
            Log.i("test" , dataBean.toString());
        }
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
        Snackbar.make(progressBar, message, Snackbar.LENGTH_SHORT).show();
    }
}
