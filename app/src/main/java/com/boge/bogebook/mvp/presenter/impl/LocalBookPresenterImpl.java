package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.mvp.presenter.LocalBookPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.LocalBookView;
import com.boge.bogebook.util.FileUtil;
import com.boge.entity.LocalAndRecomendBook;

import java.util.List;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class LocalBookPresenterImpl extends BasePresenterImpl<LocalBookView,List<LocalAndRecomendBook>> implements LocalBookPresenter {

    @Inject
    public LocalBookPresenterImpl() {
    }

    @Override
    public void searchLocalBook() {
        this.beforeRequest();
        try{
            List<LocalAndRecomendBook> localBooks = FileUtil.searchTxt();
            mView.hideProgress();
            if(mView != null){
                mView.setLocalBook(localBooks);
            }
        }catch (Exception e){
            if(mView != null){
                mView.showErrorMsg("扫描失败");
            }
        }
    }

}
