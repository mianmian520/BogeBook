package com.boge.bogebook.mvp.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.mvp.presenter.impl.SearchPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
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

    private TagAdapter<String> tagAdapter;

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

        initRecyclerView();

        searchPresenter.attachView(this);

        getHotWord();
    }

    private void initRecyclerView() {

    }

    private void initTag() {
        tagAdapter = new TagAdapter<String>(this);
        tagAdapter.setOnTagItemClick(new TagAdapter.OnTagItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                List<String> hots = tagAdapter.getmDataList();
                Snackbar.make(tvTag , hots.get(position) , Snackbar.LENGTH_SHORT).show();
            }
        });
        tvTag.setAdapter(tagAdapter);
    }

    /**
     * 菜单拦
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public void setHotWords(HotWord hotWords) {
        if (BookApplication.getHotWords() == null) {
            BookApplication.setHotWords(hotWords.getHotWords());
        }
        getHotWord();
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
}
