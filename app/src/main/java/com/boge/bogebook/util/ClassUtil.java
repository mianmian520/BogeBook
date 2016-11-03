package com.boge.bogebook.util;

import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.entity.Recommend;
import com.boge.bogebook.entity.SearchDetail;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.entity.LocalAndRecomendBook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class ClassUtil {

    public static List<LocalAndRecomendBook> RecommendToLocal(List<Recommend.RecommendBook> recommendBooks){
        List<LocalAndRecomendBook> localAndRecomendBooks = new ArrayList<LocalAndRecomendBook>();
        int i = 0;
        for (Recommend.RecommendBook recommendBook : recommendBooks){
            LocalAndRecomendBook localAndRecomendBook = new LocalAndRecomendBook();
            localAndRecomendBook.setBookId(recommendBook.get_id());
            localAndRecomendBook.setChaptersCount(recommendBook.getChaptersCount());
            localAndRecomendBook.setCover(recommendBook.getCover());
            localAndRecomendBook.setIsLocal(false);
            localAndRecomendBook.setTitle(recommendBook.getTitle());
            localAndRecomendBook.setLastChapter(recommendBook.getLastChapter());
            localAndRecomendBook.setHasUp(true);
            localAndRecomendBook.setIsTop(false);
            localAndRecomendBook.setLocation(i++);
            localAndRecomendBooks.add(localAndRecomendBook);
        }
        return localAndRecomendBooks;
    }

    public static List<BookInfo> RankingsToBookInfo(Rankings rankings) {
        if(rankings != null){
            List<Rankings.RankingBean.BooksBean> books = rankings.getRanking().getBooks();
            List<BookInfo> bookInfos = new ArrayList<BookInfo>();
            for (Rankings.RankingBean.BooksBean booksBean : books){
                BookInfo bookInfo = new BookInfo();
                bookInfo.set_id(booksBean.get_id());
                bookInfo.setAuthor(booksBean.getAuthor());
                bookInfo.setCat(booksBean.getCat());
                bookInfo.setCover(booksBean.getCover());
                bookInfo.setLatelyFollower(booksBean.getLatelyFollower());
                bookInfo.setRetentionRatio(booksBean.getRetentionRatio());
                bookInfo.setShortIntro(booksBean.getShortIntro());
                bookInfo.setSite(booksBean.getSite());
                bookInfo.setTitle(booksBean.getTitle());
                bookInfos.add(bookInfo);
            }
            return bookInfos;
        }

        return null;
    }

    public static List<BookInfo> SearchDetailToBookInfo(SearchDetail searchDetail) {
        if(searchDetail != null){
            List<SearchDetail.BooksBean> books = searchDetail.getBooks();
            List<BookInfo> bookInfos = new ArrayList<BookInfo>();
            for (SearchDetail.BooksBean booksBean : books){
                BookInfo bookInfo = new BookInfo();
                bookInfo.set_id(booksBean.get_id());
                bookInfo.setAuthor(booksBean.getAuthor());
                bookInfo.setCat(booksBean.getCat());
                bookInfo.setCover(booksBean.getCover());
                bookInfo.setLatelyFollower(booksBean.getLatelyFollower());
                bookInfo.setRetentionRatio(booksBean.getRetentionRatio());
                bookInfo.setShortIntro(booksBean.getShortIntro());
                bookInfo.setSite(booksBean.getSite());
                bookInfo.setTitle(booksBean.getTitle());
                bookInfos.add(bookInfo);
            }
            return bookInfos;
        }
        return null;
    }
}
