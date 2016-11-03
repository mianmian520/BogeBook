package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.BookDetailInteractor;
import com.boge.bogebook.mvp.interactor.impl.BookDetailInteractorImpl;
import com.boge.bogebook.mvp.presenter.BookDetailPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.BookDetailView;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class BookDetailPresenterImpl extends BasePresenterImpl<BookDetailView,BookDetail>
            implements BookDetailPresenter,RequestCallBack<BookDetail> {

    private BookDetailInteractor<BookDetail> bookDetailInteractor;

    @Inject
    public BookDetailPresenterImpl(BookDetailInteractorImpl bookDetailInteractor) {
        this.bookDetailInteractor = bookDetailInteractor;
    }

    @Override
    public void loadBookDetail(String bookID) {
        mSubscription = bookDetailInteractor.loadBookDetail(bookID , this);
    }

    @Override
    public void success(BookDetail data) {
        super.success(data);
        if(mView != null){
            mView.setBookDetail(data);
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if(mView != null){
            mView.showErrorMsg(errorMsg);
        }
    }
}
