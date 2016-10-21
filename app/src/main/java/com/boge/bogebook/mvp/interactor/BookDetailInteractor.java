package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public interface BookDetailInteractor<T> {

    Subscription loadBookDetail(String bookId , RequestCallBack<BookDetail> callBack);

}
