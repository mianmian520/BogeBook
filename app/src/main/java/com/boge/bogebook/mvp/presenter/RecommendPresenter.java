package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;
import com.boge.entity.LocalAndRecomendBook;

import java.util.List;

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

    /**
     * 加载更新信息
     */
    void loadUpdateInfo();

    void addBookcase(List<LocalAndRecomendBook> books);
}
