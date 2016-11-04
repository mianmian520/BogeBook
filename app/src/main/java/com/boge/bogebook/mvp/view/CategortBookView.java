package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by boge on 2016/10/26.
 */

public interface CategortBookView extends BaseView {

    void setCates(List<String> cates);

    void setBookInfos(List<BookInfo> bookInfos, boolean isLoad);

}
