package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.mvp.presenter.impl.CategortBookPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListDetailAdapter;
import com.boge.bogebook.mvp.ui.adapter.SimpleRecyclerViewAdapter;
import com.boge.bogebook.mvp.view.CategortBookView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class CategortBookActivity extends BaseActivity implements CategortBookView {

    @Bind(R.id.type_recyclerView)
    RecyclerView typeRecyclerView;
    @Bind(R.id.minor_recyclerView)
    RecyclerView minorRecyclerView;
    @Bind(R.id.book_recyclerView)
    RecyclerView bookRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private String gender;
    private CategoryList.MaleBean maleBean;
    private List<String> datas;

    private SimpleRecyclerViewAdapter typeAdapter;
    private SimpleRecyclerViewAdapter minorAdapter;
    private BookListDetailAdapter bookAdapter;

    private String[] types = new String[]{Constant.CateType.HOT, Constant.CateType.NEW, Constant.CateType.REPUTATION, Constant.CateType.OVER, Constant.CateType.MONTHLY};

    private String type = types[0], minor = "";

    @Inject
    CategortBookPresenterImpl categortBookPresenter;
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

        basePresenter = categortBookPresenter;
        categortBookPresenter.attachView(this);

        initBookRecyclerView();

        getCates();
    }

    /**
     * 小说列表RecyclerView
     */
    private void initBookRecyclerView() {
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookRecyclerView.setHasFixedSize(true);
        bookAdapter = new BookListDetailAdapter();
        bookAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(CategortBookActivity.this, BookDetailActivity.class);
                intent.putExtra("bookId" , bookAdapter.getBooksBeen().get(position).get_id());
                startActivity(intent);
            }
        });
        bookRecyclerView.setAdapter(bookAdapter);
        bookRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) bookRecyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition >= itemCount - 1 && newState == RecyclerView.SCROLL_STATE_IDLE){
                    categortBookPresenter.loadBookInfos();
                }
            }
        });
    }

    /**
     * 获取2级标签
     */
    private void getCates() {
        if(!gender.equals(Constant.PRESS)){
            initTypeRecyclerView();
            initMinorRecyclerView();
            categortBookPresenter.loadCategortLv2(gender , maleBean.getName());
        }else{
            typeRecyclerView.setVisibility(View.GONE);
            minorRecyclerView.setVisibility(View.GONE);
            categortBookPresenter.loadBookInfos(gender, type, maleBean.getName(), minor);
        }
    }

    /**
     * 二级分类标签RecyclerView
     */
    private void initMinorRecyclerView() {
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        minorRecyclerView.setLayoutManager(linearLayoutManager);
        minorRecyclerView.setHasFixedSize(true);
        minorAdapter = new SimpleRecyclerViewAdapter();
        minorAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0 && minor.equals("")){

                }else if(!minor.equals(minorAdapter.getDatas().get(position))){
                    if(position == 0){
                        minor = "";
                    }else {
                        minor = minorAdapter.getDatas().get(position);
                    }
                    categortBookPresenter.loadBookInfos(gender, type, maleBean.getName(), minor);
                }

            }
        });
        minorRecyclerView.setAdapter(minorAdapter);
    }

    /**
     * 类型RecyclerView （热门，新书。。。）
     */
    private void initTypeRecyclerView() {
        datas = Arrays.asList(getResources().getStringArray(R.array.type_tabs));
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeRecyclerView.setLayoutManager(linearLayoutManager);
        typeRecyclerView.setHasFixedSize(true);
        typeAdapter = new SimpleRecyclerViewAdapter(datas);
        typeAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if(!type.equals(types[position])){
                    type = types[position];
                    categortBookPresenter.loadBookInfos(gender, type, maleBean.getName(), minor);
                }
            }
        });
        typeRecyclerView.setAdapter(typeAdapter);
    }

    private void initIntent() {
        maleBean = getIntent().getParcelableExtra("maleBean");
        toolbar.setTitle(maleBean.getName());
        gender = getIntent().getStringExtra(Constant.GENDER);
    }

    /**
     * 设置二级标签并加载小说
     * @param cates
     */
    @Override
    public void setCates(List<String> cates) {
        if(cates != null && cates.size() > 0){
            cates.add(0,"全部");
            minorAdapter.setDatas(cates);
        }else{
            minorRecyclerView.setVisibility(View.GONE);
        }
        categortBookPresenter.loadBookInfos(gender, type, maleBean.getName(), minor);
    }

    @Override
    public void setBookInfos(List<BookInfo> bookInfos, boolean isLoad) {
        if (isLoad){
            bookAdapter.addAll(bookInfos);
        }else{
            bookAdapter.setBooksBeen(bookInfos);
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
        Snackbar.make(progressBar, message, Snackbar.LENGTH_LONG).show();
    }

}
