package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.SearchDetail;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.SearchInteractor;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/3
 */

public class SearchInteractorImpl implements SearchInteractor {

    @Inject
    public SearchInteractorImpl() {
    }

    @Override
    public Subscription loadHotWord(final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getHotWord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotWord>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(HotWord hotWord) {
                        callBack.success(hotWord);
                    }
                });
    }

    @Override
    public Subscription autoComplete(String query, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().autoComplete(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoComplete>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                        Log.i("test" , e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(AutoComplete autoComplete) {
                        callBack.success(autoComplete);
                    }
                });
    }

    @Override
    public Subscription searchBook(String query, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().searchBooks(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                        Log.i("test" , e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SearchDetail searchDetail) {
                        callBack.success(searchDetail);
                    }
                });
    }
}
