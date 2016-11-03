package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/3
 */

public interface SearchInteractor {

    /**
     * 加载热门搜索
     * @param callBack
     * @return
     */
    Subscription loadHotWord(RequestCallBack callBack);

}
