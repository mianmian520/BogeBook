package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/8
 */

public interface ThemeBookInteractor {

    /**
     * 加载主题书单标签
     * @param callBack
     * @return
     */
    Subscription loadTagType(RequestCallBack callBack);

    /**
     * 获取主题书单列表
     * @param duration
     * @param sort
     * @param start
     * @param limit
     * @param tag
     * @param gender
     * @param callBack
     * @return
     */
    Subscription loadBookLists(String duration, String sort, int start,
                               int limit, String tag, String gender, RequestCallBack callBack);

}
