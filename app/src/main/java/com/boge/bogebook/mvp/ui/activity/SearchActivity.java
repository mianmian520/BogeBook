package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
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
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.mvp.presenter.impl.SearchPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListDetailAdapter;
import com.boge.bogebook.mvp.ui.adapter.ImgAndTextAdapter;
import com.boge.bogebook.mvp.ui.adapter.TagAdapter;
import com.boge.bogebook.mvp.view.SearchView;
import com.boge.bogebook.util.SharedPreferencesUtil;
import com.boge.bogebook.view.TagGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static com.boge.bogebook.R.mipmap.history;

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

    private List<String> historys;

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
        String s = SharedPreferencesUtil.getInstance().getString(Constant.HISTORY);
        if(s == null){
            historys = new ArrayList<String>();
        }else{
            historys = new Gson().fromJson(s , new TypeToken<ArrayList<String>>(){}.getType());
        }
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
        bookAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(SearchActivity.this, BookDetailActivity.class);
                intent.putExtra("bookId" , bookAdapter.getBooksBeen().get(position).get_id());
                startActivity(intent);
            }
        });
        rvBook.setAdapter(bookAdapter);
    }

    private void initHistoryRecyclerView() {
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setHasFixedSize(true);
        historyAdapter = new ImgAndTextAdapter(this, historys, history, new OnBaseItemClick() {
            @Override
            public void onItemClick(View v, int position, Object data) {
                showSearchBook();
                searchPresenter.searchBook((String) data);
                addToHistory((String) data);
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
                showSearchBook();
                searchPresenter.searchBook((String) data);
                addToHistory((String) data);
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
                showSearchBook();
                searchPresenter.searchBook(hots.get(position));
                addToHistory(hots.get(position));
            }
        });
        tvTag.setAdapter(tagAdapter);
    }

    /**
     * 显示搜索的书籍recyclerView
     */
    private void showSearchBook(){
        rlHotHistory.setVisibility(View.GONE);
        rvKey.setVisibility(View.GONE);
        llSearchBook.setVisibility(View.VISIBLE);
    }

    /**
     * 添加到历史搜索
     * @param s
     */
    private void addToHistory(String s) {
        if(historys.contains(s)){
            historys.remove(historys.indexOf(s));
        } else if(historys.size() >= 7){
            historys.remove(historys.size()-1);
        }
        historys.add(0,s);
        historyAdapter.notifyDataSetChanged();
        SharedPreferencesUtil.getInstance().putString(Constant.HISTORY , new Gson().toJson(historys));
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

    /**
     * 随机获取热门搜索中的10个
     */
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
                    int i1 = 0 ;
                    while(true){
                        i1 = r.nextInt(hotWords.size());
                        if(!hots.contains(hotWords.get(i1))){
                            break;
                        }
                    }
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
                if(historys.size() != 0){
                    historys.clear();
                    historyAdapter.notifyDataSetChanged();
                }
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
                if(!query.equals("")){
                    showSearchBook();
                    addToHistory(query);
                    searchPresenter.searchBook(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals("")) {
                    rvKey.setVisibility(View.VISIBLE);
                    rlHotHistory.setVisibility(View.GONE);
                    llSearchBook.setVisibility(View.GONE);
                    searchPresenter.autoComplete(newText);
                } else {
                    if(llSearchBook.getVisibility() != View.VISIBLE){
                        rvKey.setVisibility(View.GONE);
                        rlHotHistory.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.onDestroy();
    }
}
