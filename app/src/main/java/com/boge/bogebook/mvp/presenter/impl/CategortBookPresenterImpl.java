package com.boge.bogebook.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.CategortBookInteractor;
import com.boge.bogebook.mvp.interactor.impl.CategortBookInteractorImpl;
import com.boge.bogebook.mvp.presenter.CategortBookPresenter;
import com.boge.bogebook.mvp.view.CategortBookView;
import com.boge.bogebook.mvp.view.base.BaseView;
import com.boge.bogebook.util.ClassUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/4
 */

public class CategortBookPresenterImpl implements CategortBookPresenter,RequestCallBack {

    private CategortBookView mView;

    private Subscription mSubscription;

    private CategortBookInteractor categortBookInteractor;

    private String major;
    private String type;
    private String gender;
    private String minor;
    private int start = 0;
    private int limit = 20;
    private boolean isLoad = false;

    @Inject
    public CategortBookPresenterImpl(CategortBookInteractorImpl categortBookInteractor) {
        this.categortBookInteractor = categortBookInteractor;
    }

    @Override
    public void beforeRequest() {
        if(mView != null){
            mView.showProgress();
        }
    }

    @Override
    public void success(Object data) {
        if(mView != null){
            if(data instanceof CategoryListLv2){
                List<String> list = ClassUtil.CateToList((CategoryListLv2) data, this.gender, this.major);
                mView.setCates(list);
            }else if(data instanceof BooksByCats){
                mView.hideProgress();
                List<BookInfo> bookInfos = ClassUtil.CatsToBookInfo((BooksByCats) data);
                mView.setBookInfos(bookInfos , isLoad);
            }
        }
    }

    @Override
    public void onError(String errorMsg) {
        if(mView != null){
            mView.showErrorMsg(errorMsg);
            mView.hideProgress();
        }
    }

    @Override
    public void loadCategortLv2(String gender, String major) {
        this.gender = gender;
        this.major = major;
        mSubscription = categortBookInteractor.loadCategortLv2(this);
    }

    @Override
    public void loadBookInfos(String gender, String type, String major, String minor) {
        this.gender = gender;
        this.type = type;
        this.major = major;
        this.minor = minor;
        start = 0;
        limit = 20;
        isLoad = false;
        loadBooks();
    }

    @Override
    public void loadBookInfos() {
        start += limit;
        isLoad = true;
        loadBooks();
    }

    private void loadBooks() {
        mSubscription = categortBookInteractor.loadBookInfos(gender, type, major, minor, start, limit, this);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (CategortBookView) view;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
