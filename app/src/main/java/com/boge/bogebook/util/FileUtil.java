package com.boge.bogebook.util;

import android.os.Environment;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.dao.LocalAndRecomendBookDao;
import com.boge.entity.LocalAndRecomendBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class FileUtil {

    public static List<LocalAndRecomendBook> localBooks = new ArrayList<LocalAndRecomendBook>();

    /**
     * 搜索TXT文件
     * @return
     */
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
                            .where(LocalAndRecomendBookDao.Properties.Title.eq(files[i].getName())).unique();
                    if(book == null){
                        LocalAndRecomendBook localBook = new LocalAndRecomendBook();
                        localBook.setTitle(files[i].getName());
                        localBook.setPath(files[i].getAbsolutePath());
                        localBook.setSize(files[i].length());
                        localBook.setHasUp(false);
                        localBook.setIsLocal(true);
                        localBook.setIsTop(false);
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

    public static void createDirectory(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 写入文件
     * @param filePath
     * @param content
     * @param b
     */
    private static void writeFile(String filePath, String content, boolean b) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath , b);
            byte[] bytes = content.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存章节内容
     * @param path
     * @param chapterBean
     * @param chapter
     */
    public static void saveChapterFile(String path, ChapterRead.ChapterBean chapterBean, int chapter){
        File file = getSaveChapterFile(path , chapter);
        String replace = chapterBean.getCpContent().replace("\n\n", "\n");
        replace = replace.replace("\n\n", "\n");
        FileUtil.writeFile(file.getAbsolutePath() , replace ,false);
    }

    public static File getChapterFile(String path, int currentChapter) {
        File file = new File(getChapterPath(path , currentChapter));
        if (file != null && file.length() > 50)
            return file;
        return null;
    }

    /**
     * 得到章节文件
     * @param path
     * @param chapter
     * @return
     */
    public static File getSaveChapterFile(String path, int chapter) {
        createDirectory(getChapterDirectoryPath(path));
        File file = new File(getChapterPath(path , chapter));
        if(!file.exists()){
            createFile(file);
        }
        return file;
    }

    /**
     * 得到章节文件夹目录
     * @param path
     * @return
     */
    private static String getChapterDirectoryPath(String path) {
        return Constant.BASE_PATH + File.separator + Constant.DIRECTORY + File.separator
                + Constant.CHAPTERS + File.separator + path;
    }

    /**
     * 得到章节路径
     * @param path
     * @param chapter
     * @return
     */
    private static String  getChapterPath(String path, int chapter){
        return getChapterDirectoryPath(path) + File.separator + chapter;
    }

    /**
     * 递归创建文件夹
     *
     * @param file
     * @return 创建失败返回""
     */
    public static String createFile(File file) {
        try {
            if (file.getParentFile().exists()) {
                file.createNewFile();
                return file.getAbsolutePath();
            } else {
                createDir(file.getParentFile().getAbsolutePath());
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 递归创建文件夹
     *
     * @param dirPath
     * @return 创建失败返回""
     */
    public static String createDir(String dirPath) {
        try {
            File file = new File(dirPath);
            if (file.getParentFile().exists()) {
                file.mkdir();
                return file.getAbsolutePath();
            } else {
                createDir(file.getParentFile().getAbsolutePath());
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirPath;
    }

}
