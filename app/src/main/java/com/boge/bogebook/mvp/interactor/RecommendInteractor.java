package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public interface RecommendInteractor<T> {

    Subscription loadRecommendBook(String gender , RequestCallBack<T> callBack);

    Subscription loadBookupdateInfo(String view, String id, RequestCallBack<T> callBack);

}
