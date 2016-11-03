package com.boge.bogebook.mvp.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.presenter.impl.SearchPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListDetailAdapter;
import com.boge.bogebook.mvp.ui.adapter.ImgAndTextAdapter;
import com.boge.bogebook.mvp.ui.adapter.TagAdapter;
import com.boge.bogebook.mvp.view.SearchView;
import com.boge.bogebook.view.TagGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchView {

    @Bind(R.id.tv_tag)
    TagGroup tvTag;
    @Bind(R.id.rv_history)
    RecyclerView rvHistory;
    @Bind(R.id.rl_hot_history)
    RelativeLayout rlHotHistory;
    @Bind(R.id.rv_key)
    RecyclerView rvKey;
    @Bind(R.id.tv_accurate_search)
    TextView tvAccurateSearch;
    @Bind(R.id.rv_book)
    RecyclerView rvBook;
    @Bind(R.id.ll_search_book)
    LinearLayout llSearchBook;

    private TagAdapter<String> tagAdapter;
    private ImgAndTextAdapter autoCompleteAdapter;
    private ImgAndTextAdapter historyAdapter;
    /***书籍详细列表适配器*/
    private BookListDetailAdapter bookAdapter;

    @Inject
    SearchPresenterImpl searchPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        toolbar.setTitle("");

        initTag();
        initKeyRecyclerView();
        initHistoryRecyclerView();
        initBookRecyclerView();

        basePresenter = searchPresenter;
        searchPresenter.attachView(this);

        getHotWord();
    }

    private void initBookRecyclerView() {
        rvBook.setLayoutManager(new LinearLayoutManager(this));
        rvBook.setHasFixedSize(true);
        bookAdapter = new BookListDetailAdapter();
        rvBook.setAdapter(bookAdapter);
    }

    private void initHistoryRecyclerView() {
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setHasFixedSize(true);
        historyAdapter = new ImgAndTextAdapter(this, new ArrayList<String>(), R.mipmap.history, new OnBaseItemClick() {
            @Override
            public void onItemClick(View v, int position, Object data) {

            }
        });
        rvHistory.setAdapter(historyAdapter);
    }

    private void initKeyRecyclerView() {
        rvKey.setLayoutManager(new LinearLayoutManager(this));
        rvKey.setHasFixedSize(true);
        autoCompleteAdapter = new ImgAndTextAdapter(this, new ArrayList<String>(), R.mipmap.search, new OnBaseItemClick() {
            @Override
            public void onItemClick(View v, int position, Object data) {
                rvKey.setVisibility(View.GONE);
                llSearchBook.setVisibility(View.VISIBLE);
                searchPresenter.searchBook((String) data);
            }
        });
        rvKey.setAdapter(autoCompleteAdapter);
    }

    private void initTag() {
        tagAdapter = new TagAdapter<String>(this);
        tagAdapter.setOnTagItemClick(new TagAdapter.OnTagItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                List<String> hots = tagAdapter.getmDataList();
                Snackbar.make(tvTag, hots.get(position), Snackbar.LENGTH_SHORT).show();
            }
        });
        tvTag.setAdapter(tagAdapter);
    }

    @Override
    public void setHotWords(HotWord hotWords) {
        if (BookApplication.getHotWords() == null) {
            BookApplication.setHotWords(hotWords.getHotWords());
        }
        getHotWord();
    }

    @Override
    public void setAutoComplete(AutoComplete autoComplete) {
        autoCompleteAdapter.clear();
        autoCompleteAdapter.addAll(autoComplete.getKeywords());
    }

    @Override
    public void setBooks(List<BookInfo> bookInfos) {
        if(bookInfos != null){
            bookAdapter.setBooksBeen(bookInfos);
        }
    }

    private void getHotWord() {
        List<String> hotWords = BookApplication.getHotWords();
        if (hotWords == null) {
            searchPresenter.loadHotWords();
        } else {
            List<String> hots = new ArrayList<String>();
            if (hotWords.size() < 10) {
                hots = hotWords;
            } else {
                Random r = new Random();
                for (int i = 0; i < 10; i++) {
                    int i1 = r.nextInt(hotWords.size());
                    hots.add(hotWords.get(i1));
                }
            }
            tagAdapter.onlyAddAll(hots);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(tvTag, message, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick({R.id.ll_replace, R.id.ll_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_replace:
                getHotWord();
                break;
            case R.id.ll_delete:
                break;
        }
    }

    private android.support.v7.widget.SearchView searchView;

    /**
     * 菜单拦
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);//在菜单中找到对应控件的item
        searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals("")) {
                    rvKey.setVisibility(View.VISIBLE);
                    rlHotHistory.setVisibility(View.GONE);
                    searchPresenter.autoComplete(newText);
                } else {
                    rvKey.setVisibility(View.GONE);
                    rlHotHistory.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        return true;
    }

}
