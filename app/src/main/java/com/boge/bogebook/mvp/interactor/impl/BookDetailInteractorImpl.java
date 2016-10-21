package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.BookDetailInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class BookDetailInteractorImpl implements BookDetailInteractor<BookDetail> {

    @Inject
    public BookDetailInteractorImpl() {}

    @Override
    public Subscription loadBookDetail(String bookId, final RequestCallBack<BookDetail> callBack) {
        return BookRetrofitManager.getInstance().getBookDetail(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetail>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                        Log.i("test" , e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BookDetail bookDetail) {
                        callBack.success(bookDetail);
                    }
                });
    }
}
