package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/4
 */

public interface CategortBookPresenter extends BasePresenter {

    /**
     * 加载二级分类
     * @param major 1级分类
     */
    void loadCategortLv2(String gender, String major);

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female、press
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @return
     */
    void loadBookInfos(String gender, String type, String major, String minor);

    /**
     * 加载更多
     */
    void loadBookInfos();

}
