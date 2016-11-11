package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.BookListDetail;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.BookListDetailInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/11
 */

public class BookListDetailInteractorImpl implements BookListDetailInteractor<BookListDetail> {

    @Inject
    public BookListDetailInteractorImpl() {
    }

    @Override
    public Subscription loadBookListDetail(String bookListId, final RequestCallBack<BookListDetail> callBack) {
        return BookRetrofitManager.getInstance().getBookListDetail(bookListId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookListDetail>() {
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
                    public void onNext(BookListDetail bookListDetail) {
                        callBack.success(bookListDetail);
                    }
                });
    }
}
