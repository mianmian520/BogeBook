package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public interface RecommendPresenter extends BasePresenter{

    /**
     * 根据性别加载推荐小说
     * @param gender
     */
    void loadRecommendBook(String gender);

}
