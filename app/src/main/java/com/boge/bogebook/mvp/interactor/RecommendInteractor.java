package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;
import com.boge.entity.LocalAndRecomendBook;

import java.util.List;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public interface RecommendInteractor<T> {
    /**
     * 加载书架的书籍
     * @param gender
     * @param callBack
     * @return
     */
    Subscription loadRecommendBook(String gender , RequestCallBack<T> callBack);

    /**
     * 加载书架网络图书的更新信息
     * @param view
     * @param id
     * @param callBack
     * @return
     */
    Subscription loadBookupdateInfo(String view, String id, RequestCallBack<T> callBack);

    /**
     * 添加书籍到书架
     * @param books
     */
    void addBookcase(List<LocalAndRecomendBook> books);

    /**
     * 书籍置顶
     * @param book
     */
    void bookStickied(LocalAndRecomendBook book);
}
