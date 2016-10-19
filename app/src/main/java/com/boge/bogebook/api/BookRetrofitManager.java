package com.boge.bogebook.api;

import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.entity.Recommend;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

public class BookRetrofitManager {

    public static BookRetrofitManager instance;

    private BookApiService service;

    private static OkHttpClient okHttpClient;

    public static BookRetrofitManager getInstance(){
        if(instance == null){
            instance = new BookRetrofitManager();
        }
        return instance;
    }

    public BookRetrofitManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if(okHttpClient == null){
            synchronized (BookRetrofitManager.class) {
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(12, TimeUnit.SECONDS)
                        .readTimeout(12, TimeUnit.SECONDS)
                        .writeTimeout(12, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .retryOnConnectionFailure(true)//失败重连
                        .build();
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(BookApiService.class);
    }

    /**
     * 根据性别查询推荐的小说
     * @param gender      male男    fmale女
     * @return
     */
    public Observable<Recommend> getRecomend(String gender){
        return service.getRecomend(gender);
    }

    public Observable<BookToc> getBookBToc(String bookId,  String view){
        return service.getBookBToc(bookId , view);
    }

    /**
     * 获取所有的排行榜
     * @return
     */
    public Observable<RankingList> getRankingList(){
        return service.getRankingList();
    }
}
