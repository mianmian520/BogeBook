package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public interface BookDetailPresenter extends BasePresenter {

    void loadBookDetail(String bookID);

}
