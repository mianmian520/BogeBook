package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.HotReview;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.ReviewInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/16
 */

public class ReviewInteractorImpl implements ReviewInteractor {

    @Inject
    public ReviewInteractorImpl() {
    }

    @Override
    public Subscription loadBookHotReview(String bookId, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getHotReview(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotReview>() {
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
                    public void onNext(HotReview hotReview) {
                        callBack.success(hotReview);
                    }
                });
    }
}
