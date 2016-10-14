package com.boge.bogebook.mvp.view.base;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showErrorMsg(String message);
}
