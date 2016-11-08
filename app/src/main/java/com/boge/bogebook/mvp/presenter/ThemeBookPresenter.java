package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/8
 */

public interface ThemeBookPresenter extends BasePresenter {

    /**
     * 获取主题书单标签列表
     */
    void loadTagType();

}
