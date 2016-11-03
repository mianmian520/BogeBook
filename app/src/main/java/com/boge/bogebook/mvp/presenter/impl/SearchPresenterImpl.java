package com.boge.bogebook.mvp.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.SearchDetail;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.SearchInteractor;
import com.boge.bogebook.mvp.interactor.impl.SearchInteractorImpl;
import com.boge.bogebook.mvp.presenter.SearchPresenter;
import com.boge.bogebook.mvp.view.SearchView;
import com.boge.bogebook.mvp.view.base.BaseView;
import com.boge.bogebook.util.ClassUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/3
 */

public class SearchPresenterImpl implements SearchPresenter,RequestCallBack {

    private SearchView mView;

    private SearchInteractor searchInteractor;

    private Subscription mSubscription;

    @Inject
    public SearchPresenterImpl(SearchInteractorImpl searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    @Override
    public void loadHotWords() {
        mSubscription = searchInteractor.loadHotWord(this);
    }

    @Override
    public void autoComplete(String query) {
        mSubscription = searchInteractor.autoComplete(query , this);
    }

    @Override
    public void searchBook(String query) {
        mSubscription = searchInteractor.searchBook(query , this);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (SearchView) view;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void beforeRequest() {

    }

    @Override
    public void success(Object data) {
        if(mView != null){
            if(data instanceof HotWord){
                mView.setHotWords((HotWord) data);
            } else if(data instanceof AutoComplete){
                mView.setAutoComplete((AutoComplete) data);
            } else if(data instanceof SearchDetail){
                SearchDetail searchDetail = (SearchDetail) data;
                List<BookInfo> bookInfos = ClassUtil.SearchDetailToBookInfo(searchDetail);
                for (BookInfo bookInfo : bookInfos){
                    Log.i("test" , bookInfo.toString());
                }
                mView.setBooks(bookInfos);
            }
        }
    }

    @Override
    public void onError(String errorMsg) {
        if(mView != null){
            mView.showErrorMsg(errorMsg);
        }
    }
}
