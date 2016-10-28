package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RecommendInteractor;
import com.boge.bogebook.mvp.interactor.impl.RecommendInteractorImpl;
import com.boge.bogebook.mvp.presenter.RecommendPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.RecommendView;
import com.boge.bogebook.util.ClassUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendView,List<Recommend.RecommendBook>>
        implements RecommendPresenter , RequestCallBack<List<Recommend.RecommendBook>>{

    private RecommendInteractor<List<Recommend.RecommendBook>> recommendInteractor;

    @Inject
    public RecommendPresenterImpl(RecommendInteractorImpl recommendInteractor) {
        this.recommendInteractor = recommendInteractor;
    }

    @Override
    public void loadRecommendBook(String gender) {
        this.beforeRequest();
        recommendInteractor.loadRecommendBook(gender , this);
    }

    @Override
    public void success(List<Recommend.RecommendBook> data) {
        super.success(data);
        if(mView != null){
            mView.setReCommendBook(ClassUtil.RecommendToLocal(data));
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
