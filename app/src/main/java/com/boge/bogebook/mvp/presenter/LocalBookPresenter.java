package com.boge.bogebook.mvp.presenter;

import com.boge.bogebook.mvp.presenter.base.BasePresenter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public interface LocalBookPresenter extends BasePresenter {

    /**
     * 搜索本地的TXT文件
     */
    void searchLocalBook();

}
