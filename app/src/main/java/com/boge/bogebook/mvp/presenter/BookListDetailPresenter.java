package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/11
 */

public interface BookListDetailPresenter extends BasePresenter {

    void loadBookListDetail(String bookListId);

}
