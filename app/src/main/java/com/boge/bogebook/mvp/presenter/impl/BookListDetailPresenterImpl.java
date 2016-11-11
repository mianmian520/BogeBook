package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.BookListDetail;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.BookListDetailInteractor;
import com.boge.bogebook.mvp.interactor.impl.BookListDetailInteractorImpl;
import com.boge.bogebook.mvp.presenter.BookListDetailPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.BookListDetailView;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/11
 */

public class BookListDetailPresenterImpl extends BasePresenterImpl<BookListDetailView,BookListDetail>
        implements BookListDetailPresenter,RequestCallBack<BookListDetail> {

    private BookListDetailInteractor<BookListDetail> bookListDetailInteractor;

    @Inject
    public BookListDetailPresenterImpl(BookListDetailInteractorImpl bookListDetailInteractor) {
        this.bookListDetailInteractor = bookListDetailInteractor;
    }

    @Override
    public void loadBookListDetail(String bookListId) {
        mSubscription = bookListDetailInteractor.loadBookListDetail(bookListId, this);
    }

    @Override
    public void success(BookListDetail data) {
        super.success(data);
        if(mView != null){
            mView.setBookListDetail(data.getBookList());
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
