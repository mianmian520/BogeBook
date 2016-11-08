package com.boge.bogebook.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.ThemeBookInteractor;
import com.boge.bogebook.mvp.interactor.impl.ThemeBookInteractorImpl;
import com.boge.bogebook.mvp.presenter.ThemeBookPresenter;
import com.boge.bogebook.mvp.view.ThemeBookView;
import com.boge.bogebook.mvp.view.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/8
 */

public class ThemeBookPresenterImpl implements ThemeBookPresenter,RequestCallBack {

    private ThemeBookView mView;

    private ThemeBookInteractor themeBookInteractor;

    private Subscription mSubscription;

    @Inject
    public ThemeBookPresenterImpl(ThemeBookInteractorImpl themeBookInteractor) {
        this.themeBookInteractor = themeBookInteractor;
    }

    @Override
    public void loadTagType() {
        themeBookInteractor.loadTagType(this);
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (ThemeBookView) view;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void beforeRequest() {
        if(mView != null){
            mView.showProgress();
        }
    }

    @Override
    public void success(Object data) {
        if(mView != null){
            if(data instanceof BookListTags){
                List<BookListTags.DataBean> dataBeanList = ((BookListTags) data).getData();
                List<String> tags = new ArrayList<String>();
                tags.add("男生");
                tags.add("女生");
                BookListTags.DataBean dataBean = new BookListTags.DataBean("性别", tags);
                dataBeanList.add(0,dataBean);
                mView.setTagsType(dataBeanList);
            } else {
                mView.hideProgress();
            }
        }
    }

    @Override
    public void onError(String errorMsg) {
        if(mView != null){
            mView.hideProgress();
            mView.showErrorMsg(errorMsg);
        }
    }
}
