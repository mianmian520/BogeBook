package com.boge.bogebook.api;


import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.entity.BookListDetail;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
import com.boge.bogebook.entity.BookReview;
import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.entity.CommentList;
import com.boge.bogebook.entity.DiscussionList;
import com.boge.bogebook.entity.HotReview;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.entity.RecommendBookList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/11
 */

public interface BookApi {

    /**
     * 根据性别查询推荐的小说
     * @param gender      male男    fmale女
     * @return
     */
    @GET("/book/recommend")
    Observable<Recommend> getRecomend(@Query("gender") String gender);

    /**
     * 获取所有的排行榜
     * @return
     */
    @GET("/ranking/gender")
    Observable<RankingList> getRankingList();

    /**
     * 根据排行榜的ID获取排行榜的小说
     * @param rankingId 排行榜id
     * @return
     */
    @GET("ranking/{rankingId}")
    Observable<Rankings> getRankings(@Path("rankingId") String rankingId);

    /**
     * 获取分类
     * @return
     */
    @GET("/cats/lv2/statistics")
    Observable<CategoryList> getCategoryList();

    /**
     * 获取二级分类
     *
     * @return
     */
    @GET("/cats/lv2")
    Observable<CategoryListLv2> getCategoryListLv2();

    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param start  开始下标
     * @param limit  结束下标   分页
     * @return
     */
    @GET("/book/by-categories")
    Observable<BooksByCats> getBooksByCats(@Query("gender") String gender, @Query("type") String type, @Query("major") String major, @Query("minor") String minor, @Query("start") int start, @Query("limit") int limit);

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
    @GET("/book-list")
    Observable<BookLists> getBookLists(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("tag") String tag, @Query("gender") String gender);

    /**
     * 获取主题书单标签列表
     *
     * @return
     */
    @GET("/book-list/tagType")
    Observable<BookListTags> getBookListTags();

    /**
     * 获取书单详情
     * @param bookListId 书单列表id
     * @return
     */
    @GET("/book-list/{bookListId}")
    Observable<BookListDetail> getBookListDetail(@Path("bookListId") String bookListId);

    /**
     * 获取小说详情
     * @param bookId   小说id
     * @return
     */
    @GET("/book/{bookId}")
    Observable<BookDetail> getBookDetail(@Path("bookId") String bookId);

    /**
     * 根据小说获取推荐的小说
     * @param bookId  小说id
     * @param limit   推荐数量
     * @return
     */
    @GET("/book-list/{bookId}/recommend")
    Observable<RecommendBookList> getRecommendBookList(@Path("bookId") String bookId, @Query("limit") String limit);

    /**
     * 热门评论
     *
     * @param book  书籍id
     * @return
     */
    @GET("/post/review/best-by-book")
    Observable<HotReview> getHotReview(@Query("book") String book);

    /**
     * 获取神评论列表(综合讨论区、书评区、书荒区皆为同一接口)
     *
     * @param disscussionId->_id
     * @return
     */
    @GET("/post/{disscussionId}/comment/best")
    Observable<CommentList> getBestComments(@Path("disscussionId") String disscussionId);

    /**
     * 获取书评区帖子详情
     *
     * @param bookReviewId->_id
     * @return
     */
    @GET("/post/review/{bookReviewId}")
    Observable<BookReview> getBookReviewDetail(@Path("bookReviewId") String bookReviewId);

    /**
     * 获取书评区、书荒区帖子详情内的评论列表
     *
     * @param bookReviewId->_id
     * @param start             0
     * @param limit             30
     * @return
     */
    @GET("/post/review/{bookReviewId}/comment")
    Observable<CommentList> getBookReviewComments(@Path("bookReviewId") String bookReviewId, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书籍详情讨论列表
     *
     * @param book       bookId
     * @param sort       updated(默认排序)、created(最新发布)、comment-count(最多评论)
     * @param type       normal,vote
     * @param start      0
     * @param limit      20
     * @return
     */
    @GET("/post/by-book")
    Observable<DiscussionList> getBookDetailDisscussionList(@Query("book") String book, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit);

    /**
     * 获取书籍详情书评列表
     *
     * @param book       bookId
     * @param sort       updated(默认排序)、created(最新发布)、helpful(最有用的)、comment-count(最多评论)
     * @param start      0
     * @param limit      20
     * @return
     */
    @GET("/post/review/by-book")
    Observable<HotReview> getBookDetailReviewList(@Query("book") String book, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit);

}
