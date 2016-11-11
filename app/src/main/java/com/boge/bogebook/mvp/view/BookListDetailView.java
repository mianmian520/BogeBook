package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BookListDetail;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/11
 */

public interface BookListDetailView extends BaseView {

    /**
     * 设置书单详情
     * @param bookListDetail
     */
    void setBookListDetail(BookListDetail.BookListBean bookListDetail);

}
