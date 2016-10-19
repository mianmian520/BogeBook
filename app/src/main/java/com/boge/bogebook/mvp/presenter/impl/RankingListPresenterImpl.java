package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RankingInteractor;
import com.boge.bogebook.mvp.interactor.impl.RankingInteractorImpl;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.presenter.RankingListPresenter;
import com.boge.bogebook.mvp.view.RankingView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/10/19.
 */

public class RankingListPresenterImpl extends BasePresenterImpl<RankingView,RankingList> implements
        RankingListPresenter,RequestCallBack<RankingList> {

    private RankingInteractor<RankingList> rankingInteractor;

    @Inject
    public RankingListPresenterImpl(RankingInteractorImpl rankingInteractor) {
        this.rankingInteractor = rankingInteractor;
    }

    @Override
    public void loadRankingList() {
        this.beforeRequest();
        rankingInteractor.loadRankingList(this);
    }

    @Override
    public void success(RankingList data) {
        super.success(data);
        if(mView != null){
            mView.setRankingList(data);
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
