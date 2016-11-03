package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.CategoryInteractor;
import com.boge.bogebook.mvp.interactor.impl.CategoryInteractorImpl;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.presenter.CategoryPresenter;
import com.boge.bogebook.mvp.view.CategoryView;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class CategoryPresenterImpl extends BasePresenterImpl<CategoryView , CategoryList>
            implements CategoryPresenter,RequestCallBack<CategoryList> {

    private CategoryInteractor<CategoryList> categoryInteractor;

    @Inject
    public CategoryPresenterImpl(CategoryInteractorImpl categoryInteractor) {
        this.categoryInteractor = categoryInteractor;
    }

    @Override
    public void loadCategoryList() {
        this.beforeRequest();
        mSubscription = categoryInteractor.loadCategoryList(this);
    }

    @Override
    public void success(CategoryList data) {
        super.success(data);
        if(mView != null){
            mView.setCategoryList(data);
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if(mView != null){
            mView.showErrorMsg(errorMsg);
        }
    }
}
