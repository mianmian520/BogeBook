package com.boge.bogebook.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public interface BasePresenter {

    void attachView(@NonNull BaseView view);

    void onDestroy();

}
