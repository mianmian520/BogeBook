package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
import com.boge.bogebook.mvp.view.base.BaseView;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/8
 */

public interface ThemeBookView extends BaseView {

    /**
     * 设置主题书单标签列表
     * @param dataBeanList
     */
    void setTagsType(List<BookListTags.DataBean> dataBeanList);

    /**
     * 设置书单列表
     * @param bookLists 书单列表
     * @param isLoad    是否是加载的数据
     */
    void setBookList(List<BookLists.BookListsBean> bookLists, boolean isLoad);
}
