package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.RequestCallBack;

import java.util.List;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/8
 */

public interface AuthorInteractor<T> {

    Subscription loadAuthorToBook(String author , RequestCallBack<List<BookInfo>> callBack);

    Subscription loadTagToBook(String tags, int start, int limit, RequestCallBack<List<BookInfo>> callBack);
}
