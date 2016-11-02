package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/2
 */

public interface ReaderPresenter extends BasePresenter {

    /**
     * 加载书籍的章节
     * @param bookid
     */
    void loadBookToc(String bookid);

    /**
     * 加载章节内容
     * @param url
     */
    void loadChapterContent(String url , int chapter);
}
