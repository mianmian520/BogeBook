package com.boge.bogebook.mvp.interactor.impl;

import android.util.Log;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.ReaderInteractor;

import org.json.JSONArray;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/2
 */

public class ReaderInteractorImpl implements ReaderInteractor {

    @Inject
    public ReaderInteractorImpl() {
    }

    @Override
    public Subscription getChaptersId(String bookid, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getChapterId(Constant.SUMMARY,bookid)
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        try {
                            String s = responseBody.string();
                            JSONArray jsonArray = new JSONArray(s);
                            String id = jsonArray.getJSONObject(0).getString("_id");
                            return id;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return "";
                    }
                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
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
                    public void onNext(String s) {
                        Log.i("test" , s);
                        callBack.success(s);
                    }
                });
    }

    @Override
    public Subscription loadBookToc(String bookid, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getBookBToc(bookid , Constant.CHAPTERS)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookToc>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(BookToc bookToc) {
                        callBack.success(bookToc);
                    }
                });
    }

    @Override
    public Subscription loadChapterContent(String url, final int chapter, final RequestCallBack callBack) {
        return BookRetrofitManager.getInstance().getChapterRead(url)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChapterRead>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(ChapterRead chapterRead) {
                        callBack.success(chapter);
                        callBack.success(chapterRead);
                    }
                });
    }
}
