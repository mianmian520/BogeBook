package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RankDetailInteractor;
import com.boge.bogebook.mvp.interactor.impl.RankDetailInteractorImpl;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.presenter.RankDetailPresenter;
import com.boge.bogebook.mvp.view.RankDetailView;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class RankDetailPresenterImpl extends BasePresenterImpl<RankDetailView,Rankings>
        implements RankDetailPresenter,RequestCallBack<Rankings> {

    private RankDetailInteractor<Rankings> rankDetailInteractor;

    @Inject
    public RankDetailPresenterImpl(RankDetailInteractorImpl rankDetailInteractor) {
        this.rankDetailInteractor = rankDetailInteractor;
    }

    @Override
    public void loadRankDetail(String rankingId) {
        this.beforeRequest();
        rankDetailInteractor.loadRankDetail(rankingId , this);
    }

    @Override
    public void success(Rankings data) {
        super.success(data);
        if(mView != null){
            mView.setRankings(data);
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
