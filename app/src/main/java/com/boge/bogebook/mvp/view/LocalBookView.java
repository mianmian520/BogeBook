package com.boge.bogebook.mvp.view;

import com.boge.bogebook.mvp.view.base.BaseView;
import com.boge.entity.LocalAndRecomendBook;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public interface LocalBookView extends BaseView {

    /**
     * 设置本地的读书
     * @param localBooks
     */
    void setLocalBook(List<LocalAndRecomendBook> localBooks);

}
