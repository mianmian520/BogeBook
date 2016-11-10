package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.mvp.presenter.impl.ThemeBookPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListAdapter;
import com.boge.bogebook.mvp.ui.adapter.SimpleRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.TagRecyclerViewAdapter;
import com.boge.bogebook.mvp.view.ThemeBookView;
import com.boge.bogebook.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class ThemeBookActivity extends BaseActivity implements ThemeBookView {

    @Bind(R.id.type_recyclerView)
    RecyclerView typeRecyclerView;
    @Bind(R.id.tag_recyclerView)
    RecyclerView tagRecyclerView;
    @Bind(R.id.iv_up_down)
    ImageView ivUpDown;
    @Bind(R.id.book_recyclerView)
    RecyclerView bookRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.rv_tags)
    RecyclerView rvTags;

    private List<String> datas;
    /***标签列表*/
    private List<String> tags;
    /***当前选中的标签*/
    private String tag;

    private String type;

    private SimpleRecyclerViewAdapter typeAdapter;
    private SimpleRecyclerViewAdapter tagAdapter;

    private TagRecyclerViewAdapter tagRecyclerViewAdapter;

    private BookListAdapter bookListAdapter;

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
        initrvTags();
        initBookRecyclerView();

        basePresenter = themeBookPresenter;
        themeBookPresenter.attachView(this);
        themeBookPresenter.loadTagType();
        themeBookPresenter.loadBooksList(type, tag);
    }

    private void initBookRecyclerView() {
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookRecyclerView.setHasFixedSize(true);
        bookListAdapter = new BookListAdapter(this,null);
        bookListAdapter.setOnBaseItemClick(new OnBaseItemClick<BookLists.BookListsBean>() {
            @Override
            public void onItemClick(View v, int position, BookLists.BookListsBean data) {
                Intent intent = new Intent(ThemeBookActivity.this, BookListDetailActivity.class);
                intent.putExtra(Constant.BOOKLIST_ID, data.get_id());
                startActivity(intent);
            }
        });
        bookRecyclerView.setAdapter(bookListAdapter);
        bookRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) bookRecyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition >= itemCount - 1 && newState == RecyclerView.SCROLL_STATE_IDLE){
                    themeBookPresenter.loadMore();
                }
            }
        });
    }

    private void initrvTags() {
        rvTags.setLayoutManager(new LinearLayoutManager(this));
        rvTags.setHasFixedSize(true);
        tagRecyclerViewAdapter = new TagRecyclerViewAdapter(this , null, tag, new OnBaseItemClick() {
            @Override
            public void onItemClick(View v, int position, Object data) {
                tag = (String) data;
                if(tags.contains(tag)){
                    tagAdapter.setChooseMap(tags.indexOf(tag));
                    tagAdapter.notifyDataSetChanged();
                }else{
                    tags.add(1,tag);
                    tags.remove(tags.size()-1);
                    tagAdapter.setDatas(tags, 1);
                }
                themeBookPresenter.loadBooksList(type, tag);
                rvTags.setVisibility(View.GONE);
                ivUpDown.setImageResource(R.mipmap.down);
                bookRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        rvTags.setAdapter(tagRecyclerViewAdapter);
    }

    private void initTagRecyclerView() {
        String s = SharedPreferencesUtil.getInstance().getString(Constant.BOOK_TAG);
        tags = new Gson().fromJson(s, new TypeToken<ArrayList<String>>() {}.getType());
        tag = SharedPreferencesUtil.getInstance().getString(Constant.CHOOSE_TAG);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tagRecyclerView.setLayoutManager(linearLayoutManager);
        tagRecyclerView.setHasFixedSize(true);
        tagAdapter = new SimpleRecyclerViewAdapter(tags,tags.indexOf(tag));
        tagAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if(!tag.equals(tags.get(position))){
                    tag = tags.get(position);
                    tagRecyclerViewAdapter.setTag(tag);
                    themeBookPresenter.loadBooksList(type, tag);
                }
            }
        });
        tagRecyclerView.setAdapter(tagAdapter);
    }

    /**
     * 类型RecyclerView （本周最热，最新发布,最多收藏）
     */
    private void initTypeRecyclerView() {
        datas = Arrays.asList(getResources().getStringArray(R.array.duration_tabs));
        type = datas.get(0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        typeRecyclerView.setLayoutManager(linearLayoutManager);
        typeRecyclerView.setHasFixedSize(true);
        typeAdapter = new SimpleRecyclerViewAdapter(datas);
        typeAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                if(!type.equals(datas.get(position))){
                    type = datas.get(position);
                    themeBookPresenter.loadBooksList(type, tag);
                }
            }
        });
        typeRecyclerView.setAdapter(typeAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferencesUtil.getInstance().putString(Constant.BOOK_TAG, new Gson().toJson(tags));
        SharedPreferencesUtil.getInstance().putString(Constant.CHOOSE_TAG, tag);
    }

    @Override
    public void setTagsType(List<BookListTags.DataBean> dataBeanList) {
        tagRecyclerViewAdapter.setmList(dataBeanList);
    }

    @Override
    public void setBookList(List<BookLists.BookListsBean> bookLists, boolean isLoad) {
        if (isLoad){
            bookListAdapter.addAll(bookLists);
        } else {
            bookListAdapter.setmList(bookLists);
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

    @OnClick(R.id.ll_screening)
    public void onClick() {
        if(rvTags.getVisibility() == View.VISIBLE){
            rvTags.setVisibility(View.GONE);
            ivUpDown.setImageResource(R.mipmap.down);
            bookRecyclerView.setVisibility(View.VISIBLE);
        } else {
            rvTags.setVisibility(View.VISIBLE);
            ivUpDown.setImageResource(R.mipmap.up);
            bookRecyclerView.setVisibility(View.GONE);
        }
    }
}
