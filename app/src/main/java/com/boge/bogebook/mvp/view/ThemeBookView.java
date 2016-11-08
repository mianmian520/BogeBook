package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BookListTags;
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

}
