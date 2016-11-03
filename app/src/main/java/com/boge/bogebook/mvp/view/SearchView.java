package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.mvp.view.base.BaseView;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/3
 */

public interface SearchView extends BaseView {

    /**
     * 设置热门搜索
     * @param hotWords
     */
    void setHotWords(HotWord hotWords);

    /**
     * 关键字补全信息
     * @param autoComplete
     */
    void setAutoComplete(AutoComplete autoComplete);

    /**
     * 小说信息
     * @param bookInfos
     */
    void setBooks(List<BookInfo> bookInfos);
}
