package com.boge.bogebook.util;

import com.boge.bogebook.entity.Recommend;
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

}
