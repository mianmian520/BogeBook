package com.boge.bogebook.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.SearchInteractor;
import com.boge.bogebook.mvp.interactor.impl.SearchInteractorImpl;
import com.boge.bogebook.mvp.presenter.SearchPresenter;
import com.boge.bogebook.mvp.view.SearchView;
import com.boge.bogebook.mvp.view.base.BaseView;

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
