package com.boge.bogebook.mvp.ui.activity;

import android.widget.FrameLayout;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.listener.OnReadStateChangeListener;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.view.readView.PageFactory;
import com.boge.bogebook.view.readView.PageWidget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ReaderActivity extends BaseActivity implements OnReadStateChangeListener{

    @Bind(R.id.reader)
    FrameLayout reader;
    private String path;
    private boolean isLocal;


    private PageWidget mPageWidget;

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

        initPagerWidget();
    }

    private PageFactory pageFactory;

    private void initPagerWidget() {
        List<BookToc.ChaptersBean> chaptersBeens = new ArrayList<BookToc.ChaptersBean>();
        BookToc.ChaptersBean chaptersBean = new BookToc.ChaptersBean();
        chaptersBean.setTitle("第一章");
        chaptersBeens.add(chaptersBean);
        mPageWidget = new PageWidget(this, path , chaptersBeens, this);// 页面
        reader.removeAllViews();
        reader.addView(mPageWidget);
        if (!mPageWidget.isPrepared) {
            mPageWidget.init();
        } else {
            mPageWidget.jumpToChapter(1);
        }
    }

    @Override
    public void onChapterChanged(int chapter) {

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
