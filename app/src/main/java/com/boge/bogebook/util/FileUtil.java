package com.boge.bogebook.util;

import android.os.Environment;

import com.boge.bogebook.BookApplication;
import com.boge.dao.LocalAndRecomendBookDao;
import com.boge.entity.LocalAndRecomendBook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class FileUtil {

    public static List<LocalAndRecomendBook> localBooks = new ArrayList<LocalAndRecomendBook>();
    public static List<LocalAndRecomendBook> searchTxt(){
        File file = new File(getSDCardPath());
        localBooks.clear();
        search(file);
        return localBooks;
    }
    private static void search(File file){
        File[] files = file.listFiles();
        for (int i = 0 ; i < files.length ; i++){
            if(!files[i].isDirectory()){
                if(files[i].getAbsolutePath().endsWith(".txt") && files[i].length()>100*1024){
                    LocalAndRecomendBook book = BookApplication.getLocalAndRecomendBookDao().queryBuilder()
                            .where(LocalAndRecomendBookDao.Properties.Path.eq(files[i].getName())).unique();
                    if(book == null){
                        LocalAndRecomendBook localBook = new LocalAndRecomendBook();
                        localBook.setTitle(files[i].getName());
                        localBook.setPath(files[i].getAbsolutePath());
                        localBook.setSize(files[i].length());
                        localBook.setHasUp(false);
                        localBook.setLocal(true);
                        localBooks.add(localBook);
                    }
                }
            } else {
                search(files[i]);
            }
        }
    }

    /**
     * 外部SD卡
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

}
