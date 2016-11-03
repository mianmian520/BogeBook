package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RankDetailInteractor;
import com.boge.bogebook.util.ClassUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class RankDetailInteractorImpl implements RankDetailInteractor<List<BookInfo>> {

    @Inject
    public RankDetailInteractorImpl() {
    }

    @Override
    public Subscription loadRankDetail(String rankingId, final RequestCallBack<List<BookInfo>> callBack) {
        return BookRetrofitManager.getInstance().getRankings(rankingId)
                .map(new Func1<Rankings, List<BookInfo>>() {
                    @Override
                    public List<BookInfo> call(Rankings rankings) {

                        return ClassUtil.RankingsToBookInfo(rankings);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BookInfo>>() {
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
                    public void onNext(List<BookInfo> rankings) {
                        callBack.success(rankings);
                    }
                });
    }
}
