package com.boge.bogebook.util;

import com.boge.bogebook.bean.LocalAndRecomendBook;
import com.boge.bogebook.entity.Recommend;

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
        for (Recommend.RecommendBook recommendBook : recommendBooks){
            LocalAndRecomendBook localAndRecomendBook = new LocalAndRecomendBook();
            localAndRecomendBook.set_id(recommendBook.get_id());
            localAndRecomendBook.setChaptersCount(recommendBook.getChaptersCount());
            localAndRecomendBook.setCover(recommendBook.getCover());
            localAndRecomendBook.setLocal(false);
            localAndRecomendBook.setTitle(recommendBook.getTitle());
            localAndRecomendBook.setLastChapter(recommendBook.getLastChapter());
            localAndRecomendBook.setHasUp(false);
            localAndRecomendBooks.add(localAndRecomendBook);
        }
        return localAndRecomendBooks;
    }

}
