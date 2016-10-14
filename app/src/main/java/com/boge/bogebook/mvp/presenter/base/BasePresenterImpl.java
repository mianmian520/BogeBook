package com.boge.bogebook.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.view.base.BaseView;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public class BasePresenterImpl<T extends BaseView , E> implements BasePresenter,RequestCallBack<E> {

    protected T mView;

    protected Subscription mSubscription;


    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (T) view;
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
    public void success(E data) {
        if(mView != null){
            mView.hideProgress();
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
