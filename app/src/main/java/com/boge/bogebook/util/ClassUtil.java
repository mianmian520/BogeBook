package com.boge.bogebook.util;

import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryListLv2;
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

    public static List<String> CateToList(CategoryListLv2 data, String gender, String major) {
        List<CategoryListLv2.MaleBean> males;
        if(gender.equals(Constant.MALE)){
            males = data.getMale();
        } else if(gender.equals(Constant.FEMALE)){
            males = data.getFemale();
        } else {
            return null;
        }
        for (CategoryListLv2.MaleBean maleBean : males){
            if(maleBean.getMajor().equals(major)){
                return maleBean.getMins();
            }
        }
        return null;
    }

    public static List<BookInfo> CatsToBookInfo(BooksByCats data) {
        List<BookInfo> bookInfos = new ArrayList<BookInfo>();
        for (BooksByCats.BooksBean booksBean : data.getBooks()){
            BookInfo bookInfo = new BookInfo();
            bookInfo.set_id(booksBean.get_id());
            bookInfo.setAuthor(booksBean.getAuthor());
            bookInfo.setCat(booksBean.getMajorCate());
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
}
