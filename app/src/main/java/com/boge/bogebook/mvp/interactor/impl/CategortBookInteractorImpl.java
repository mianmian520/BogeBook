package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.CategortBookInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/4
 */

public class CategortBookInteractorImpl implements CategortBookInteractor {

    @Inject
    public CategortBookInteractorImpl() {
    }

    @Override
    public Subscription loadCategortLv2(final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getCategoryListLv2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryListLv2>() {
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
                    public void onNext(CategoryListLv2 categoryListLv2) {
                        callBack.success(categoryListLv2);
                    }
                });
    }

    @Override
    public Subscription loadBookInfos(String gender, @Constant.CateType String type, String major, String minor, int start, int limit, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getBooksByCats(gender, type, major, minor, start, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BooksByCats>() {
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
                    public void onNext(BooksByCats booksByCats) {
                        callBack.success(booksByCats);
                    }
                });
    }
}
