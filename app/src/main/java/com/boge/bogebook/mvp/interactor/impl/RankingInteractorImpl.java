package com.boge.bogebook.mvp.interactor.impl;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RankingInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/19.
 */

public class RankingInteractorImpl implements RankingInteractor<RankingList> {

    @Inject
    public RankingInteractorImpl() {}


    @Override
    public Subscription loadRankingList(final RequestCallBack<RankingList> callBack) {
        return BookRetrofitManager.getInstance().getRankingList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RankingList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(RankingList rankingList) {
                        callBack.success(rankingList);
                    }
                });
    }


}
