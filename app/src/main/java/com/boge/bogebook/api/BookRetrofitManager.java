package com.boge.bogebook.api;

import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.BookUpdateInfo;
import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.entity.RecommendBookList;
import com.boge.bogebook.entity.SearchDetail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    public Observable<ResponseBody> getChapterId(String view , String bookid){
        return service.getChapterID(view , bookid);
    }

    public Observable<BookToc> getBookBToc(String bookId,  String view){
        return service.getBookBToc(bookId , view);
    }

    public Observable<BookToc> getBookABToc(String bookId,  String view){
        return service.getBookBToc(bookId , view);
    }

    /**
     * 获取章节内容
     * @param url    章节Url
     * @return
     */
    public Observable<ChapterRead> getChapterRead(String url){
        return service.getChapterRead(url);
    }

    /**
     * 获取所有的排行榜
     * @return
     */
    public Observable<RankingList> getRankingList(){
        return service.getRankingList();
    }

    /**
     * 根据排行榜的ID获取排行榜的小说获取单一排行榜
     * @param rankingId 排行榜id
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     * @return
     */
    public Observable<Rankings> getRankings(String rankingId){
        return service.getRankings(rankingId);
    }

    /**
     * 获取小说详情
     * @param bookId   小说id
     * @return
     */
    public Observable<BookDetail> getBookDetail( String bookId){
        return service.getBookDetail(bookId);
    }

    /**
     * 根据小说获取推荐的小说
     * @param bookId  小说id
     * @param limit   推荐数量
     * @return
     */
    public Observable<RecommendBookList> getRecommendBookList( String bookId,String limit){
        return service.getRecommendBookList(bookId , limit);
    }

    /**
     * 获取分类
     * @return
     */
    public Observable<CategoryList> getCategoryList(){
        return service.getCategoryList();
    }

    /**
     * 获取二级分类
     *
     * @return
     */
    public Observable<CategoryListLv2> getCategoryListLv2(){
        return service.getCategoryListLv2();
    }

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结) monthly(包月)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param start  开始下标
     * @param limit  结束下标   分页
     * @return
     */
    public Observable<BooksByCats> getBooksByCats(String gender, String type, String major, String minor, int start, int limit){
        return service.getBooksByCats(gender, type, major, minor, start, limit);
    }

    /**
     * 获取主题书单标签列表
     *
     * @return
     */
    public Observable<BookListTags> getBookListTags(){
        return service.getBookListTags();
    }

    /**
     * 获取主题书单列表
     * 本周最热：duration=last-seven-days&sort=collectorCount
     * 最新发布：duration=all&sort=created
     * 最多收藏：duration=all&sort=collectorCount
     *
     * @param tag    都市、古代、架空、重生、玄幻、网游
     * @param gender male、female
     * @param tag 标签
     * @param limit  结束下标
     * @return
     */
    public Observable<BookLists> getBookLists(String duration, String sort, int start,
                                        int limit, String tag, String gender){
        return service.getBookLists(duration, sort, start, limit, tag, gender);
    }

    /**
     * 得到小说的更新信息
     * @param view
     * @param id
     * @return
     */
    public Observable<List<BookUpdateInfo>> getBookUpdateInfo(String view , String id){
        return service.getBookUpdateInfo(view, id);
    }

    /**
     * 热门搜索
     * @return
     */
    public Observable<HotWord> getHotWord(){
        return service.getHotWord();
    }

    /**
     * 关键字自动补全
     *
     * @param query
     * @return
     */
    public Observable<AutoComplete> autoComplete(String query){
        return service.autoComplete(query);
    }

    /**
     * 书籍查询
     *
     * @param query
     * @return
     */
    public Observable<SearchDetail> searchBooks(String query){
        return service.searchBooks(query);
    }

}
