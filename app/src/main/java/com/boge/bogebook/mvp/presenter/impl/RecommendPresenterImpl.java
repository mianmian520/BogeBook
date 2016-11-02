package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.manager.dbmanager.LARBManager;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RecommendInteractor;
import com.boge.bogebook.mvp.interactor.impl.RecommendInteractorImpl;
import com.boge.bogebook.mvp.presenter.RecommendPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.RecommendView;
import com.boge.entity.LocalAndRecomendBook;

import java.util.List;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendView,List<LocalAndRecomendBook>>
        implements RecommendPresenter , RequestCallBack<List<LocalAndRecomendBook>>{

    private RecommendInteractor<List<LocalAndRecomendBook>> recommendInteractor;

    private boolean isRefresh = false;

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
    public void loadUpdateInfo() {
        isRefresh = true;
        String bookID = LARBManager.getBookID();
        recommendInteractor.loadBookupdateInfo("updated" , bookID , this);
    }

    @Override
    public void addBookcase(List<LocalAndRecomendBook> books) {
        recommendInteractor.addBookcase(books);
        mView.addBookCase(books);
    }

    @Override
    public void bookStickied(LocalAndRecomendBook book) {
        recommendInteractor.bookStickied(book);
    }

    @Override
    public void success(List<LocalAndRecomendBook> data) {
        super.success(data);
        if(mView != null){
            if(isRefresh){
                mView.setBookUpdateInfo(data);
                isRefresh = false;
            }else{
                mView.setReCommendBook(data);
            }
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
