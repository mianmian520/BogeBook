package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/2
 */

public interface ReaderView extends BaseView{

    void setBookToc(BookToc bookToc);

    void showChapterRead(ChapterRead.ChapterBean chapterBean , int chapter);

}
