package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public interface BookDetailView extends BaseView {

    void setBookDetail(BookDetail bookDetail);

}
