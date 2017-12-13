package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.HotReview;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.ReviewInteractor;
import com.boge.bogebook.mvp.interactor.impl.ReviewInteractorImpl;
import com.boge.bogebook.mvp.presenter.ReviewPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.BookDetailView;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/16
 */

public class ReviewPresenterImpl extends BasePresenterImpl implements ReviewPresenter,
        RequestCallBack {

    private ReviewInteractor reviewInteractor;

    @Inject
    public ReviewPresenterImpl(ReviewInteractorImpl reviewInteractor) {
        this.reviewInteractor = reviewInteractor;
    }

    @Override
    public void loadBookHotReview(String bookId) {
        reviewInteractor.loadBookHotReview(bookId, this);
    }

    @Override
    public void success(Object data) {
        super.success(data);
        if(mView != null){
            if(data instanceof HotReview){
                if(mView instanceof BookDetailView){
                    BookDetailView view = (BookDetailView) mView;
                    view.setBookHotReview((HotReview) data);
                }
            }
        }
    }
}
