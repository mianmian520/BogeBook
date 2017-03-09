package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/8
 */

public interface AuthorPresenter extends BasePresenter {

    /**
     * 根据作者查询书籍
     * @param author
     */
    void loadAuthorToBook(String author);

    /**
     * 根据标签查询书籍
     * @param tags
     */
    void loadTagToBook(String tags);

    /**
     * 加载更多
     */
    void loadBooks();
}
