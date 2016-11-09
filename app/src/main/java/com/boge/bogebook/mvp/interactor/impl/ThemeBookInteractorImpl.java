package com.boge.bogebook.mvp.interactor.impl;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.ThemeBookInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/8
 */

public class ThemeBookInteractorImpl implements ThemeBookInteractor {

    @Inject
    public ThemeBookInteractorImpl() {}

    @Override
    public Subscription loadTagType(final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getBookListTags()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookListTags>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(BookListTags bookListTags) {
                        callBack.success(bookListTags);
                    }
                });
    }

    @Override
    public Subscription loadBookLists(String duration, String sort, int start,
                                      int limit, String tag, String gender, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getBookLists(duration, sort, start, limit, tag, gender)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookLists>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(BookLists bookLists) {
                        callBack.success(bookLists);
                    }
                });
    }
}
