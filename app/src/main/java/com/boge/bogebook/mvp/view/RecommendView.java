package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BookUpdateInfo;
import com.boge.bogebook.mvp.view.base.BaseView;
import com.boge.entity.LocalAndRecomendBook;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public interface RecommendView extends BaseView {

    /**
     * 初始化推荐小说
     * @param recommendBookList
     */
    void setReCommendBook(List<LocalAndRecomendBook> books);

    /**
     * 设置小说更新信息
     * @param bookUpdateInfos
     */
    void setBookUpdateInfo(List<LocalAndRecomendBook> books);

    /**
     * 添加书籍
     * @param books
     */
    void addBookCase(List<LocalAndRecomendBook> books);
}
