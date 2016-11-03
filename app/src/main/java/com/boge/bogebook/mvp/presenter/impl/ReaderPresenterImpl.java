package com.boge.bogebook.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.ReaderInteractor;
import com.boge.bogebook.mvp.interactor.impl.ReaderInteractorImpl;
import com.boge.bogebook.mvp.presenter.ReaderPresenter;
import com.boge.bogebook.mvp.view.ReaderView;
import com.boge.bogebook.mvp.view.base.BaseView;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/2
 */

public class ReaderPresenterImpl implements ReaderPresenter,RequestCallBack {

    private ReaderView mView;

    private Subscription mSubscription;

    private ReaderInteractor readerInteractor;
    private int chapter = 0;

    @Inject
    public ReaderPresenterImpl(ReaderInteractorImpl readerInteractor) {
        this.readerInteractor = readerInteractor;
    }

    @Override
    public void loadBookToc(String bookid) {
        mSubscription = readerInteractor.getChaptersId(bookid , this);
    }

    @Override
    public void loadChapterContent(String url, int chapter) {
        mSubscription = readerInteractor.loadChapterContent(url, chapter, this);
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
            mView.hideProgress();
        }
        if(data instanceof String){
            mSubscription = readerInteractor.loadBookToc((String) data, this);
        }else if(data instanceof BookToc){
            mView.setBookToc((BookToc) data);
        }else if(data instanceof ChapterRead){
            mView.showChapterRead(((ChapterRead)data).getChapter() , this.chapter);
        }else if(data instanceof Integer){
            this.chapter = (int) data;
        }
    }

    @Override
    public void onError(String errorMsg) {
        if(mView != null){
            mView.hideProgress();
            mView.showErrorMsg(errorMsg);
        }
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (ReaderView) view;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
