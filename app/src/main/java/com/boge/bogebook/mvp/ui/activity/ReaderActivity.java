package com.boge.bogebook.mvp.ui.activity;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.listener.OnReadStateChangeListener;
import com.boge.bogebook.mvp.presenter.impl.ReaderPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.view.ReaderView;
import com.boge.bogebook.util.FileUtil;
import com.boge.bogebook.view.readView.PageWidget;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class ReaderActivity extends BaseActivity implements ReaderView,OnReadStateChangeListener{

    @Bind(R.id.reader)
    FrameLayout reader;
    private String path;
    private boolean isLocal;
    private String title;

    private PageWidget mPageWidget;

    /**
     * 当前章
     */
    private int currentChapter = 1;

    /**
     * 是否已经开始阅读了
     */
    private boolean startRead = false;

    @Inject
    ReaderPresenterImpl readerPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reader;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        isLocal = getIntent().getBooleanExtra(Constant.LOCAL, true);
        path = getIntent().getStringExtra(Constant.PATH);
        title = getIntent().getStringExtra(Constant.TITLE);

        initPagerWidget();
    }

    private List<BookToc.ChaptersBean> chaptersBeens = new ArrayList<BookToc.ChaptersBean>();

    private void initPagerWidget() {
        mPageWidget = new PageWidget(this, path , chaptersBeens, this, isLocal);// 页面
        reader.removeAllViews();
        reader.addView(mPageWidget);
        if(isLocal){
            BookToc.ChaptersBean chaptersBean = new BookToc.ChaptersBean();
            chaptersBean.setTitle(title);
            chaptersBeens.add(chaptersBean);
            showChapterRead(null , currentChapter);
        }else{
            readerPresenter.attachView(this);
            readerPresenter.loadBookToc(path);
        }
    }

    @Override
    public void setBookToc(BookToc bookToc) {
        chaptersBeens.clear();
        chaptersBeens.addAll(bookToc.getChapters());
        readCurrentChapter();
    }

    private void readCurrentChapter() {
        if(FileUtil.getChapterFile(path , currentChapter) != null){
            showChapterRead(null , currentChapter);
        }else{
            readerPresenter.loadChapterContent(chaptersBeens.get(currentChapter - 1).getLink() , currentChapter);
        }
    }

    @Override
    public void showChapterRead(ChapterRead.ChapterBean chapterBean, int chapter) {
        if(chapterBean != null && !isLocal){
            FileUtil.saveChapterFile(path , chapterBean , chapter);
        }
        if(!startRead){
            startRead = true;
            if (!mPageWidget.isPrepared) {
                mPageWidget.init();
            } else {
                mPageWidget.jumpToChapter(chapter);
            }
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(reader, message, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onChapterChanged(int chapter) {
        Log.i("test" , "onChapterChanged:" + chapter);
        currentChapter = chapter;
        // 加载前一节 与 后三节
        for (int i = chapter - 1; i <= chapter + 3 && i <= chaptersBeens.size(); i++) {
            if (i > 0 && i != chapter && FileUtil.getChapterFile(path , i)==null){
                readerPresenter.loadChapterContent(chaptersBeens.get(i-1).getLink() , i);
            }
        }
    }

    @Override
    public void onPageChanged(int chapter, int page) {

    }

    @Override
    public void onLoadChapterFailure(int chapter) {

    }

    @Override
    public void onCenterClick() {

    }

    @Override
    public void onFlip() {

    }
}
