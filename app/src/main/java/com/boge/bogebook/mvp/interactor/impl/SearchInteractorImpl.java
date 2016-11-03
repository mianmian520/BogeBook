package com.boge.bogebook.mvp.interactor.impl;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.HotWord;
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
}
