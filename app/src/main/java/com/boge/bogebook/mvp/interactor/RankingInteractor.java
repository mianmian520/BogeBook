package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * Created by boge on 2016/10/19.
 */

public interface RankingInteractor<T>  {

    Subscription loadRankingList(RequestCallBack<T> callBack);

}
