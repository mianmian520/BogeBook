package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/3
 */

public interface SearchPresenter extends BasePresenter {

    /**
     * 加载热门搜索
     */
    void loadHotWords();

    /**
     * 关键字补全
     * @param query
     */
    void autoComplete(String query);

    /**
     * 搜索书籍
     * @param query
     */
    void searchBook(String query);
}
