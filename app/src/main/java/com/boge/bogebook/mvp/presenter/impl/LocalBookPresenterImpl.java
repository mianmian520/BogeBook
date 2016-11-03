package com.boge.bogebook.mvp.presenter.impl;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.manager.dbmanager.LARBManager;
import com.boge.bogebook.mvp.presenter.LocalBookPresenter;
import com.boge.bogebook.mvp.presenter.base.BasePresenterImpl;
import com.boge.bogebook.mvp.view.LocalBookView;
import com.boge.bogebook.util.FileUtil;
import com.boge.dao.LocalAndRecomendBookDao;
import com.boge.entity.LocalAndRecomendBook;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class LocalBookPresenterImpl extends BasePresenterImpl<LocalBookView,LocalAndRecomendBook>
        implements LocalBookPresenter {

    @Inject
    public LocalBookPresenterImpl() {
    }

    @Override
    public void searchLocalBook() {
        this.beforeRequest();
        Observable.create(new Observable.OnSubscribe<LocalAndRecomendBook>() {
            @Override
            public void call(Subscriber<? super LocalAndRecomendBook> subscriber) {
                search(new File(FileUtil.getSDCardPath()), subscriber);
                subscriber.onCompleted();
            }
        }).buffer(500 , TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LocalAndRecomendBook>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showErrorMsg("扫描失败");
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(List<LocalAndRecomendBook> localAndRecomendBooks) {
                        mView.setLocalBook(localAndRecomendBooks);
                    }
                });
    }

    private void search(File file, Subscriber<? super LocalAndRecomendBook> subscriber) {
        File[] files = file.listFiles();
        for (int i = 0 ; i < files.length ; i++){
            if(!files[i].isDirectory()){
                if(files[i].getAbsolutePath().endsWith(".txt") && files[i].length()>512*1024){
                    LocalAndRecomendBook book = BookApplication.getLocalAndRecomendBookDao().queryBuilder()
                            .where(LocalAndRecomendBookDao.Properties.Title.eq(files[i].getName())).unique();
                    if(book == null){
                        LocalAndRecomendBook localBook = new LocalAndRecomendBook();
                        localBook.setTitle(files[i].getName());
                        localBook.setPath(files[i].getAbsolutePath());
                        localBook.setSize(files[i].length());
                        localBook.setHasUp(false);
                        localBook.setIsLocal(true);
                        localBook.setIsTop(false);
                        if(!LARBManager.isExists(localBook)){
                            subscriber.onNext(localBook);
                        }
                    }
                }
            } else {
                if(!files[i].getAbsolutePath().contains("com.") && !files[i].getAbsolutePath().contains("DCIM")){
                    search(files[i] , subscriber);
                }
            }
        }
    }


}
