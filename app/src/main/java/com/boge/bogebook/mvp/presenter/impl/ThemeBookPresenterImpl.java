package com.boge.bogebook.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
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

    private int start = 0;
    private int limit = 10;
    private String duration = "";
    private String sort = "";
    private String tag = "";
    private String gender = "";

    private boolean isLoad = false;


    @Inject
    public ThemeBookPresenterImpl(ThemeBookInteractorImpl themeBookInteractor) {
        this.themeBookInteractor = themeBookInteractor;
    }

    @Override
    public void loadTagType() {
        themeBookInteractor.loadTagType(this);
    }

    @Override
    public void loadBooksList(String type, String tag) {
        beforeRequest();
        isLoad = false;
        start = 0;
        limit = 10;
        setTagAndGender(tag);
        setDurationAndSort(type);
        loadBooks();
    }

    @Override
    public void loadMore() {
        isLoad = true;
        start += limit;
        loadBooks();
    }

    private void loadBooks(){
        mSubscription = themeBookInteractor.loadBookLists(duration, sort, start, limit, tag, gender, this);
    }

    /**
     * 设置类型
     * @param type
     */
    private void setDurationAndSort(String type) {
        if(type.equals("本周最热")){
            duration = "last-seven-days";
            sort = "collectorCount";
        } else if(type.equals("最新发布")){
            duration = "all";
            sort = "created";
        } else if(type.equals("最多收藏")){
            duration = "all";
            sort = "collectorCount";
        }
    }

    /**
     * 设置标签
     * @param tag
     */
    private void setTagAndGender(String tag){
        if(tag.equals("男生")){
            this.tag = "";
            gender = Constant.MALE;
        } else if(tag.equals("女生")){
            this.tag = "";
            gender = Constant.FEMALE;
        } else if(tag.equals("全部")){
            this.tag = "";
            gender = "";
        } else {
            this.tag = tag;
            gender = "";
        }
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
            } else if(data instanceof BookLists){
                BookLists book = (BookLists) data;
                mView.setBookList(book.getBookLists(), isLoad);
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
