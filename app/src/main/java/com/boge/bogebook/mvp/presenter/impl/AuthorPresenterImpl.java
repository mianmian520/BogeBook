package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.AuthorInteractor;
import com.boge.bogebook.mvp.interactor.impl.AuthorInteractorImpl;
import com.boge.bogebook.mvp.presenter.AuthorPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.AuthorView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/8
 */

public class AuthorPresenterImpl extends BasePresenterImpl<AuthorView,List<BookInfo>> implements AuthorPresenter,
        RequestCallBack<List<BookInfo>>{

    private AuthorInteractor<List<BookInfo>> authorInteractor;

    private int start = 0;
    private int limit = 50;
    private String tags;
    private boolean isTags = false;

    @Inject
    public AuthorPresenterImpl(AuthorInteractorImpl authorInteractor) {
        this.authorInteractor = authorInteractor;
    }

    @Override
    public void loadAuthorToBook(String author) {
        isTags = false;
        authorInteractor.loadAuthorToBook(author, this);
    }

    @Override
    public void loadTagToBook(String tags) {
        isTags = true;
        this.tags = tags;
        authorInteractor.loadTagToBook(tags, start, limit, this);
    }

    @Override
    public void loadBooks() {
        isTags = true;
        start += limit;
        authorInteractor.loadTagToBook(tags, start, limit, this);
    }

    @Override
    public void success(List<BookInfo> data) {
        if(mView != null){
            mView.setBooks(data, isTags);
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
