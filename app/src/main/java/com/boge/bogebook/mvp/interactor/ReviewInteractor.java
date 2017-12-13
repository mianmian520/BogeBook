package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/16
 */

public interface ReviewInteractor {

    Subscription loadBookHotReview(String bookId, RequestCallBack callBack);

}
