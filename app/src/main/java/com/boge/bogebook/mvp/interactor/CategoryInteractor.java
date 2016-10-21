package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public interface CategoryInteractor<T> {

    /**
     * 加载一级分类
     */
    Subscription loadCategoryList(RequestCallBack<CategoryList> callBack);

}
