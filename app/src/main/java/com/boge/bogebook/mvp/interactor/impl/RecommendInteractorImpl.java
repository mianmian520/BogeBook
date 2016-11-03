package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.manager.dbmanager.LARBManager;
import com.boge.bogebook.entity.BookUpdateInfo;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.RecommendInteractor;
import com.boge.bogebook.util.ClassUtil;
import com.boge.entity.LocalAndRecomendBook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public class RecommendInteractorImpl implements RecommendInteractor<List<LocalAndRecomendBook>> {

    @Inject
    public RecommendInteractorImpl() {
    }

    @Override
    public Subscription loadRecommendBook(String gender , final RequestCallBack callBack) {


        if(LARBManager.getCount() != 0){
            return Observable.create(new Observable.OnSubscribe<List<LocalAndRecomendBook>>() {
                @Override
                public void call(Subscriber<? super List<LocalAndRecomendBook>> subscriber) {
                    List<LocalAndRecomendBook> list = LARBManager.getAllBook();
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<LocalAndRecomendBook>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            callBack.onError(BookApplication.getmContext().getResources().getString(R.string.db_error));
                            Log.i("test" , e.getMessage());
                        }

                        @Override
                        public void onNext(List<LocalAndRecomendBook> localAndRecomendBooks) {
                            callBack.success(localAndRecomendBooks);
                        }
                    });
        }else {
            return BookRetrofitManager.getInstance()
                .getRecomend(gender)
                .map(new Func1<Recommend, List<Recommend.RecommendBook>>() {
                    @Override
                    public List<Recommend.RecommendBook> call(Recommend recommend) {
                        return recommend.getBooks();
                    }
                })
                .map(new Func1<List<Recommend.RecommendBook>, List<LocalAndRecomendBook>>() {
                    @Override
                    public List<LocalAndRecomendBook> call(List<Recommend.RecommendBook> recommendBooks) {
                        List<LocalAndRecomendBook> localAndRecomendBooks = ClassUtil.RecommendToLocal(recommendBooks);
                        for (LocalAndRecomendBook book : localAndRecomendBooks){
                            LARBManager.insertBook(book);
                        }
                        return localAndRecomendBooks;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LocalAndRecomendBook>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                        Log.i("test" , e.getMessage());
                    }

                    @Override
                    public void onNext(List<LocalAndRecomendBook> recommend) {
                        callBack.success(recommend);
                    }
                });
        }
    }

    @Override
    public Subscription loadBookupdateInfo(String view, String id, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance()
                .getBookUpdateInfo(view , id)
                .map(new Func1<List<BookUpdateInfo>, List<LocalAndRecomendBook>>() {
                    @Override
                    public List<LocalAndRecomendBook> call(List<BookUpdateInfo> bookUpdateInfos) {
                        List<LocalAndRecomendBook> books = new ArrayList<LocalAndRecomendBook>();
                        for (BookUpdateInfo updateInfo : bookUpdateInfos){
                            LocalAndRecomendBook book = LARBManager.getBook(updateInfo.get_id());
                            if(!book.getLastChapter().equals(updateInfo.getLastChapter())){
                                book.setLastChapter(updateInfo.getLastChapter());
                                book.setHasUp(true);
                                LARBManager.updateBook(book);
                                books.add(book);
                            }
                        }
                        return books;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LocalAndRecomendBook>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                        Log.i("test" , e.getMessage());
                    }

                    @Override
                    public void onNext(List<LocalAndRecomendBook> localAndRecomendBooks) {
                        callBack.success(localAndRecomendBooks);
                    }
                });
    }

    @Override
    public void addBookcase(List<LocalAndRecomendBook> books) {
        int maxIndex = LARBManager.getMaxIndex();
        for (LocalAndRecomendBook book : books){
            book.setLocation(maxIndex++);
            LARBManager.insertBook(book);
        }
    }

    private ExecutorService mSingleThreadPool;

    @Override
    public void bookStickied(final LocalAndRecomendBook book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LocalAndRecomendBook> books = LARBManager.getBeforeInLocation(book);
                increaseOrReduceIndexAndUpdate(books , true);
                book.setLocation(0);
                LARBManager.updateBook(book);
            }
        }).start();
    }

    @Override
    public void bookOnclick(final LocalAndRecomendBook book , final int location) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LocalAndRecomendBook> books  = LARBManager.getBeforeInLocation(location);
                if(LARBManager.getBookStickied() != null && books.size() > 0){
                    books.remove(0);
                    book.setLocation(1);
                }else {
                    book.setLocation(0);
                }
                increaseOrReduceIndexAndUpdate(books , true);
                LARBManager.updateBook(book);
            }
        }).start();
    }

    /**
     * 位置的增与减
     * @param books
     * @param b    true增 false 减
     */
    private void increaseOrReduceIndexAndUpdate(List<LocalAndRecomendBook> books, boolean b) {
        for (LocalAndRecomendBook book : books){
            increaseOrReduceIndex(book , b);
            LARBManager.updateBook(book);
        }
    }

    private void increaseOrReduceIndex(LocalAndRecomendBook book, boolean b) {
        int location;
        if(b){
            if(book.getIsTop()){
                book.setIsTop(false);
            }
            location = book.getLocation()+1;
        }else{
            location = book.getLocation()-1;
        }
        book.setLocation(location);
    }


    private void createThreadPool() {
        if (mSingleThreadPool == null) {
            mSingleThreadPool = Executors.newSingleThreadExecutor();
        }
    }
}
