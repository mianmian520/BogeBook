package com.boge.bogebook.mvp.interactor.impl;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.api.BookRetrofitManager;
import com.boge.bogebook.entity.BooksByTag;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.RequestCallBack;
import com.boge.bogebook.mvp.interactor.AuthorInteractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * @date 2017/3/8
 */

public class AuthorInteractorImpl implements AuthorInteractor<List<BookInfo>> {

    @Inject
    public AuthorInteractorImpl() {
    }

    @Override
    public Subscription loadAuthorToBook(String author, final RequestCallBack<List<BookInfo>> callBack) {
        return BookRetrofitManager.getInstance().searchBooksByAuthor(author)
                .map(new Func1<BooksByTag, List<BookInfo>>() {
                    @Override
                    public List<BookInfo> call(BooksByTag booksByTag) {
                        return getBookInfos(booksByTag);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BookInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(List<BookInfo> bookInfos) {
                        callBack.success(bookInfos);
                    }
                });
    }

    @Override
    public Subscription loadTagToBook(String tags, int start, int limit, final RequestCallBack<List<BookInfo>> callBack) {
        return BookRetrofitManager.getInstance().searchBookByTag(tags, start, limit)
                .map(new Func1<ResponseBody, List<BookInfo>>() {
                    @Override
                    public List<BookInfo> call(ResponseBody body) {
                        return getBookInfos(body);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BookInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(BookApplication.getmContext().getResources().getString(R.string.net_error));
                    }

                    @Override
                    public void onNext(List<BookInfo> bookInfos) {
                        callBack.success(bookInfos);
                    }
                });
    }

    private List<BookInfo> getBookInfos(ResponseBody responseBody){
        List<BookInfo> bookInfos = new ArrayList<BookInfo>();
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            if(jsonObject.optBoolean("ok")){
                JSONArray books = jsonObject.getJSONArray("books");
                for(int i = 0; i < books.length(); i++){
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setTitle(books.optJSONObject(i).optString("title"));
                    bookInfo.set_id(books.optJSONObject(i).optString("_id"));
                    bookInfo.setCover(books.optJSONObject(i).optString("cover"));
                    bookInfo.setShortIntro(books.optJSONObject(i).optString("shortIntro"));
                    bookInfo.setCat(books.optJSONObject(i).optString("tags"));
                    bookInfos.add(bookInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookInfos;
    }

    private List<BookInfo> getBookInfos(BooksByTag booksByTag){
        List<BookInfo> bookInfos = new ArrayList<BookInfo>();
        if(booksByTag.isOk()){
            List<BooksByTag.BooksBean> books = booksByTag.getBooks();
            for (BooksByTag.BooksBean book : books){
                BookInfo bookInfo = new BookInfo();
                bookInfo.setLastChapter(book.getLastChapter());
                bookInfo.setTitle(book.getTitle());
                bookInfo.setSite(book.getSite());
                bookInfo.setCover(book.getCover());
                bookInfo.setShortIntro(book.getShortIntro());
                bookInfo.set_id(book.get_id());
                bookInfo.setAuthor(book.getAuthor());
                bookInfo.setCat(book.getCat());
                bookInfo.setLatelyFollower(book.getLatelyFollower());
                bookInfo.setRetentionRatio(String.valueOf(book.getRetentionRatio()));
                bookInfos.add(bookInfo);
            }
        }
        return bookInfos;
    }
}
