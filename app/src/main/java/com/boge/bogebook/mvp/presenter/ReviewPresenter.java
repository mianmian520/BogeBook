package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/16
 */

public interface ReviewPresenter extends BasePresenter {

    void loadBookHotReview(String bookId);

}
