package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.mvp.view.base.BaseView;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/8
 */

public interface AuthorView extends BaseView {

    /**
     * 小说信息
     * @param bookInfos
     */
    void setBooks(List<BookInfo> bookInfos, boolean isTags);

}
