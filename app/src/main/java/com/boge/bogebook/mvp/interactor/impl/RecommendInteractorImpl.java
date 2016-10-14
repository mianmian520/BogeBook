package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RecommendInteractor;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public class RecommendInteractorImpl implements RecommendInteractor<List<Recommend.RecommendBook>> {

    @Inject
    public RecommendInteractorImpl() {
    }

    @Override
    public Subscription loadRecommendBook(String gender , final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance()
                .getRecomend(gender)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Recommend>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                        Log.i("test" , e.getMessage());
                    }

                    @Override
                    public void onNext(Recommend recommend) {
                        if(recommend.isOk()){
                            callBack.success(recommend.getBooks());
                        }else{
                            callBack.success(null);
                        }
                    }
                });
    }
}
