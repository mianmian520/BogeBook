package com.boge.bogebook.api;


import com.boge.bogebook.entity.AutoComplete;
import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.entity.BookHelp;
import com.boge.bogebook.entity.BookHelpList;
import com.boge.bogebook.entity.BookListDetail;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.entity.BookLists;
import com.boge.bogebook.entity.BookReview;
import com.boge.bogebook.entity.BookReviewList;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.BookUpdateInfo;
import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.BooksByTag;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.entity.CommentList;
import com.boge.bogebook.entity.DiscussionList;
import com.boge.bogebook.entity.Disscussion;
import com.boge.bogebook.entity.HotReview;
import com.boge.bogebook.entity.HotWord;
import com.boge.bogebook.entity.PublicsCount;
import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.entity.RecommendBookList;
import com.boge.bogebook.entity.SearchDetail;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/11
 */

public interface BookApiService {

    /**
     * 根据性别查询推荐的小说
     * @param gender      male男    fmale女
     * @return
     */
    @GET("/book/recommend")
    Observable<Recommend> getRecomend(@Query("gender") String gender);

    /**
     * 得到小说的更新信息
     * @param view
     * @param id
     * @return
     */
    @GET("/book")
    Observable<List<BookUpdateInfo>> getBookUpdateInfo(@Query("view") String view , @Query("id") String id);

    /**
     * 得到章节id
     * @param view
     * @param bookid
     * @return
     */
    @GET("/btoc")
    Observable<ResponseBody> getChapterID(@Query("view") String view , @Query("book") String bookid);

    /**
     * 小说章节
     * @param bookId   id
     * @param view     chapters
     * @return
     */
    @GET("/btoc/{bookId}")
    Observable<BookToc> getBookBToc(@Path("bookId") String bookId, @Query("view") String view);

    @GET("/mix-atoc/{bookId}")
    Observable<BookToc> getBookAToc(@Path("bookId") String bookId, @Query("view") String view);

    /**
     * 获取章节内容
     * @param url    章节Url
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Observable<ChapterRead> getChapterRead(@Path("url") String url);


    /**
     * 获取所有的排行榜
     * @return
     */
    @GET("/ranking/gender")
    Observable<RankingList> getRankingList();

    /**
     * 根据排行榜的ID获取排行榜的小说获取单一排行榜
     * @param rankingId 排行榜id
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     * @return
     */
    @GET("/ranking/{rankingId}")
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
    Observable<BookLists> getBookLists(@Query("duration") String duration, @Query("sort") String sort, @Query("start") int start, @Query("limit") int limit, @Query("tag") String tag, @Query("gender") String gender);

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
     * 热门搜索
     * @return
     */
    @GET("/book/hot-word")
    Observable<HotWord> getHotWord();

    /**
     * 关键字自动补全
     *
     * @param query
     * @return
     */
    @GET("/book/auto-complete")
    Observable<AutoComplete> autoComplete(@Query("query") String query);

    /**
     * 书籍查询
     *
     * @param query
     * @return
     */
    @GET("/book/fuzzy-search")
    Observable<SearchDetail> searchBooks(@Query("query") String query);

    /**
     * 通过作者查询书名
     *
     * @param author
     * @return
     */
    @GET("/book/accurate-search")
    Observable<BooksByTag> searchBooksByAuthor(@Query("author") String author);

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
     * 获取综合讨论区帖子详情
     *
     * @param disscussionId->_id
     * @return
     */
    @GET("/post/{disscussionId}")
    Observable<Disscussion> getBookDisscussionDetail(@Path("disscussionId") String disscussionId);

    /**
     * 获取综合讨论区帖子详情内的评论列表
     *
     * @param disscussionId->_id
     * @param start              0
     * @param limit              30
     * @return
     */
    @GET("/post/{disscussionId}/comment")
    Observable<CommentList> getBookDisscussionComments(@Path("disscussionId") String disscussionId, @Query("start") String start, @Query("limit") String limit);

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

    /**
     * 书籍评论数量
     * @param bookId   id
     * @return
     */
    @GET("/post/post-count-by-book")
    Observable<ResponseBody> postCountByBook(@Query("bookId") String bookId);

    /**
     * 书籍评论总数量
     * @param bookId   id
     * @return
     */
    @GET("/post/total-count")
    Observable<ResponseBody> postTotalCount(@Query("books") String bookId);

    /**
     * 获取综合讨论区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/by-block?block=ramble&duration=all&sort=updated&type=all&start=0&limit=20&distillate=true
     *
     * @param block      ramble  综合讨论   original原创
     * @param duration   all
     * @param sort       updated(默认排序)、created(最新发布)、comment-count(最多评论)
     * @param type       all
     * @param start      0
     * @param limit      20
     * @param distillate true(精品)
     * @return
     */
    @GET("/post/by-block")
    Observable<DiscussionList> getBookDisscussionList(@Query("block") String block, @Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书评区帖子列表
     * 全部、全部类型、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=all&start=0&limit=20&distillate=
     * 精品、玄幻奇幻、默认排序  http://api.zhuishushenqi.com/post/review?duration=all&sort=updated&type=xhqh&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)、created(最新发布)、helpful(最有用的)、comment-count(最多评论)
     * @param type       all(全部类型)、xhqh(玄幻奇幻)、dsyn(都市异能)
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/review")
    Observable<BookReviewList> getBookReviewList(@Query("duration") String duration, @Query("sort") String sort, @Query("type") String type, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书荒区帖子列表
     * 全部、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=
     * 精品、默认排序  http://api.zhuishushenqi.com/post/help?duration=all&sort=updated&start=0&limit=20&distillate=true
     *
     * @param duration   all
     * @param sort       updated(默认排序)、created(最新发布)、comment-count(最多评论)
     * @param start      0
     * @param limit      20
     * @param distillate true(精品) 、空字符（全部）
     * @return
     */
    @GET("/post/help")
    Observable<BookHelpList> getBookHelpList(@Query("duration") String duration, @Query("sort") String sort, @Query("start") String start, @Query("limit") String limit, @Query("distillate") String distillate);

    /**
     * 获取书荒区帖子详情
     *
     * @param helpId->_id
     * @return
     */
    @GET("/post/help/{helpId}")
    Observable<BookHelp> getBookHelpDetail(@Path("helpId") String helpId);

    /**
     * 公共帖子的数量
     * @return
     */
    @GET("/post/post-count")
    Observable<PublicsCount> getPublicsCount();
}
