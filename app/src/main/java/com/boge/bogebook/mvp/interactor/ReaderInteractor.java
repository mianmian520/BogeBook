package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/2
 */

public interface ReaderInteractor {

    Subscription getChaptersId(String bookid , RequestCallBack callBack);

    Subscription loadBookToc(String bookid , RequestCallBack callBack);

    Subscription loadChapterContent(String url, int chapter , RequestCallBack callBack);

}
