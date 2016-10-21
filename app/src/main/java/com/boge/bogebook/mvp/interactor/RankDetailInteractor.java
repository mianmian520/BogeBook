package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public interface RankDetailInteractor<T> {

    Subscription loadRankDetail(String rankingId , RequestCallBack<T> callBack);

}
