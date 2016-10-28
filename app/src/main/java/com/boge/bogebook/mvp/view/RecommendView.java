package com.boge.bogebook.mvp.view;

import com.boge.bogebook.bean.LocalAndRecomendBook;
import com.boge.bogebook.mvp.view.base.BaseView;

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
    void setReCommendBook(List<LocalAndRecomendBook> recommendBookList);

}
