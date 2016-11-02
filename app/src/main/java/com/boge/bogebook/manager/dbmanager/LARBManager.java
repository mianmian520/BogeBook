package com.boge.bogebook.manager.dbmanager;

import com.boge.bogebook.BookApplication;
import com.boge.dao.LocalAndRecomendBookDao;
import com.boge.entity.LocalAndRecomendBook;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * 书架管理
 * @author boge
 * @version 1.0
 * @date 2016/10/31
 */

public class LARBManager {

    public static LocalAndRecomendBookDao dao = BookApplication.getLocalAndRecomendBookDao();

    /**
     * 得到书架的数量
     * @return
     */
    public static int getCount(){
        return (int) dao.queryBuilder().buildCount().count();
    }

    /**
     * 得到书架所有的书籍
     * @return
     */
    public static List<LocalAndRecomendBook> getAllBook(){
        return dao.queryBuilder().orderDesc(LocalAndRecomendBookDao.Properties.IsTop)
                .orderAsc(LocalAndRecomendBookDao.Properties.Location).build().list();
    }

    /**
     * 根据是否是本地获取读书
     * @param isLocal
     * @return
     */
    public static List<LocalAndRecomendBook> getLocalBooks(boolean isLocal){
        return dao.queryBuilder().where(LocalAndRecomendBookDao.Properties.IsLocal.eq(isLocal))
                .build().list();
    }

    /**
     * 根据bookid得到书籍
     * @param bookId
     * @return
     */
    public static LocalAndRecomendBook getBook(String bookId){
        return dao.queryBuilder().where(LocalAndRecomendBookDao.Properties.BookId.eq(bookId))
                .unique();
    }

    /**
     * 得到置顶的书籍
     * @return
     */
    public static LocalAndRecomendBook getBookStickied(){
        return dao.queryBuilder().where(LocalAndRecomendBookDao.Properties.IsTop.eq(true))
                .unique();
    }

    /**
     * 添加书籍到书架
     * @param book
     */
    public static void insertBook(LocalAndRecomendBook book){
        dao.insert(book);
    }

    /**
     * 修改
     * @param book
     */
    public static void updateBook(LocalAndRecomendBook book){
        dao.update(book);
    }

    /**
     * 得到网络书籍的id集合转为字符串
     * @return
     */
    public static String getBookID(){
        List<LocalAndRecomendBook> localBooks = getLocalBooks(false);
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < localBooks.size()-1 ; i++){
            sb.append(localBooks.get(i).getBookId()).append(",");
        }
        if(localBooks.size()>0)
        sb.append(localBooks.get(localBooks.size()-1).getBookId());
        return sb.toString();
    }

    public static void deleteBook(LocalAndRecomendBook book){
        dao.delete(book);
    }

    /**
     * 得到最大的位置+1
     * @return
     */
    public static int getMaxIndex(){
        LocalAndRecomendBook unique = dao.queryBuilder().where(new WhereCondition.StringCondition("location =( select max(location) from LOCAL_AND_RECOMEND_BOOK)"))
                .build().unique();
        if(unique != null){
            return unique.getLocation()+1;
        }
        return 0;
    }

    public static List<LocalAndRecomendBook> getBeforeInLocation(LocalAndRecomendBook book){
        return dao.queryBuilder().where(LocalAndRecomendBookDao.Properties.Location.lt(book.getLocation()))
                .list();
    }
}
