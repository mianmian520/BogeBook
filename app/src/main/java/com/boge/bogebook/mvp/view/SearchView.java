package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.mvp.view.base.BaseView;

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


}
