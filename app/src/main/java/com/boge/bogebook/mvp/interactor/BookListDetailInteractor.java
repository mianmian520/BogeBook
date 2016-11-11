package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/11
 */

public interface BookListDetailInteractor<T> {

    Subscription loadBookListDetail(String bookListId, RequestCallBack<T> callBack);

}
