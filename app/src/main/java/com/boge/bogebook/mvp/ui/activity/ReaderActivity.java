package com.boge.bogebook.mvp.ui.activity;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.entity.ChapterRead;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.listener.OnReadStateChangeListener;
import com.boge.bogebook.manager.SettingManager;
import com.boge.bogebook.manager.ThemeManager;
import com.boge.bogebook.mvp.presenter.impl.ReaderPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.ReadThemeAdapter;
import com.boge.bogebook.mvp.view.ReaderView;
import com.boge.bogebook.util.FileUtil;
import com.boge.bogebook.util.ScreenUtils;
import com.boge.bogebook.view.readView.PageWidget;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static android.R.attr.data;
import static android.R.attr.theme;

public class ReaderActivity extends BaseActivity implements ReaderView, OnReadStateChangeListener{

    @Bind(R.id.reader)
    FrameLayout reader;

    @Bind(R.id.llBookReadBottom)
    LinearLayout llBookReadBottom;

    @Bind(R.id.rl_reader)
    RelativeLayout rlReader;

    @Bind(R.id.rlReadAaSet)
    LinearLayout rlReadAaSet;

    @Bind(R.id.seekbarFontSize)
    SeekBar seekbarFontSize;

    @Bind(R.id.seekbarLightness)
    SeekBar seekbarLightness;

    @Bind(R.id.cbVolume)
    CheckBox cbVolume;
    @Bind(R.id.cbAutoBrightness)
    CheckBox cbAutoBrightness;
    @Bind(R.id.rv_theme)
    RecyclerView rvTheme;

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

    private ReadThemeAdapter readThemeAdapter;

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

        initAASet();
    }

    private void initAASet() {
        seekbarFontSize.setMax(40);
        seekbarFontSize.setProgress(ScreenUtils.pxToDpInt(SettingManager.getInstance().getReadFontSize()));
        seekbarFontSize.setOnSeekBarChangeListener(new SeekBarChangeListener());

        seekbarLightness.setMax(100);
        seekbarLightness.setOnSeekBarChangeListener(new SeekBarChangeListener());
        seekbarLightness.setProgress(SettingManager.getInstance().getReadBrightness());

        cbVolume.setChecked(SettingManager.getInstance().isVolumeFlipEnable());
        cbVolume.setOnCheckedChangeListener(new ChechBoxChangeListener());

        cbAutoBrightness.setChecked(SettingManager.getInstance().isAutoBrightness());
        cbAutoBrightness.setOnCheckedChangeListener(new ChechBoxChangeListener());

        int curTheme = SettingManager.getInstance().getReadTheme();

        readThemeAdapter = new ReadThemeAdapter(this, ThemeManager.getReaderThemeData(), curTheme, new OnBaseItemClick<Integer>() {
            @Override
            public void onItemClick(View v, int position, Integer data) {
                mPageWidget.setTheme(data);
                if(data == ThemeManager.NIGHT){
                    mPageWidget.setTextColor(Color.WHITE, Color.WHITE);
                }else {
                    mPageWidget.setTextColor(Color.BLACK, Color.BLACK);
                }
                readThemeAdapter.setSelected(position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTheme.setLayoutManager(linearLayoutManager);
        rvTheme.setAdapter(readThemeAdapter);

    }

    private List<BookToc.ChaptersBean> chaptersBeens = new ArrayList<BookToc.ChaptersBean>();

    private void initPagerWidget() {
        mPageWidget = new PageWidget(this, path, chaptersBeens, this, isLocal);// 页面
        reader.removeAllViews();
        reader.addView(mPageWidget);
        if (isLocal) {
            BookToc.ChaptersBean chaptersBean = new BookToc.ChaptersBean();
            chaptersBean.setTitle(title);
            chaptersBeens.add(chaptersBean);
            showChapterRead(null, currentChapter);
        } else {
            basePresenter = readerPresenter;
            readerPresenter.attachView(this);
            readerPresenter.loadBookToc(path);
        }
    }

    private void showReadBar() { // 显示工具栏
        visible(llBookReadBottom);
    }

    private void hideReadBar() {
        gone(llBookReadBottom, rlReadAaSet);
    }

    private void toggleReadBar() {
        if (isVisible(llBookReadBottom)) {
            hideReadBar();
        } else {
            showReadBar();
        }
    }

    @Override
    public void setBookToc(BookToc bookToc) {
        chaptersBeens.clear();
        chaptersBeens.addAll(bookToc.getChapters());
        readCurrentChapter();
    }

    private void readCurrentChapter() {
        int[] readProgress = SettingManager.getInstance().getReadProgress(path);
        currentChapter = readProgress[0];
        if (FileUtil.getChapterFile(path, currentChapter) != null) {
            showChapterRead(null, currentChapter);
        } else {
            readerPresenter.loadChapterContent(chaptersBeens.get(currentChapter - 1).getLink(), currentChapter);
        }
    }

    @Override
    public void showChapterRead(ChapterRead.ChapterBean chapterBean, int chapter) {
        if (chapterBean != null && !isLocal) {
            FileUtil.saveChapterFile(path, chapterBean, chapter);
        }
        if (!startRead) {
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
        Log.i("test", "onChapterChanged:" + chapter);
        currentChapter = chapter;
        // 加载前一节 与 后三节
        for (int i = chapter - 1; i <= chapter + 3 && i <= chaptersBeens.size(); i++) {
            if (i > 0 && i != chapter && FileUtil.getChapterFile(path, i) == null) {
                readerPresenter.loadChapterContent(chaptersBeens.get(i - 1).getLink(), i);
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
        toggleReadBar();
    }

    @Override
    public void onFlip() {
        hideReadBar();
    }

    @OnClick({R.id.tvBookReadMode, R.id.tvBookReadSettings, R.id.tvBookReadDownload, R.id.tvBookReadToc})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBookReadMode:
                break;
            case R.id.tvBookReadSettings:
                if (isVisible(llBookReadBottom)) {
                    if (isVisible(rlReadAaSet)) {
                        gone(rlReadAaSet);
                    } else {
                        visible(rlReadAaSet);
                    }
                }
                break;
            case R.id.tvBookReadDownload:
                break;
            case R.id.tvBookReadToc:
                break;
        }
    }

    @OnClick({R.id.ivBrightnessMinus, R.id.ivBrightnessPlus, R.id.tvFontsizeMinus, R.id.tvFontsizePlus})
    public void setting(View view) {
        switch (view.getId()) {
            case R.id.ivBrightnessMinus:
                int curBrightness = SettingManager.getInstance().getReadBrightness();
                if (curBrightness > 2 && !SettingManager.getInstance().isAutoBrightness()) {
                    seekbarLightness.setProgress((curBrightness = curBrightness - 2));
                    ScreenUtils.setScreenBrightness(curBrightness, ReaderActivity.this);
                    SettingManager.getInstance().saveReadBrightness(curBrightness);
                }
                break;
            case R.id.ivBrightnessPlus:
                int curBrightness1 = SettingManager.getInstance().getReadBrightness();
                if (curBrightness1 < 99 && !SettingManager.getInstance().isAutoBrightness()) {
                    seekbarLightness.setProgress((curBrightness1 = curBrightness1 + 2));
                    ScreenUtils.setScreenBrightness(curBrightness1, ReaderActivity.this);
                    SettingManager.getInstance().saveReadBrightness(curBrightness1);
                }
                break;
            case R.id.tvFontsizeMinus:
                int curFontSize = SettingManager.getInstance().getReadFontSize();
                int curFontSizeDp = ScreenUtils.pxToDpInt(curFontSize);
                if (curFontSizeDp > 5) {
                    seekbarFontSize.setProgress(--curFontSizeDp);
                    mPageWidget.setFontSize(ScreenUtils.dpToPxInt(curFontSizeDp));
                }
                break;
            case R.id.tvFontsizePlus:
                int curFontSize1 = SettingManager.getInstance().getReadFontSize();
                int curFontSizeDp1 = ScreenUtils.pxToDpInt(curFontSize1);
                if (curFontSizeDp1 < 40) {
                    seekbarFontSize.setProgress(++curFontSizeDp1);
                    mPageWidget.setFontSize(ScreenUtils.dpToPxInt(curFontSizeDp1));
                }
                break;
        }
    }

    public class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(seekBar.getId() == seekbarFontSize.getId() && fromUser){
                if(progress > 5){
                    mPageWidget.setFontSize(ScreenUtils.dpToPxInt(progress));
                }
            }else if(seekBar.getId() == seekbarLightness.getId() && fromUser){
                ScreenUtils.setScreenBrightness(progress, ReaderActivity.this);
                SettingManager.getInstance().saveReadBrightness(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    class ChechBoxChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == cbVolume.getId()) {
                SettingManager.getInstance().saveVolumeFlipEnable(isChecked);
            } else if (buttonView.getId() == cbAutoBrightness.getId()) {
                if (isChecked) {
                    startAutoLightness();
                } else {
                    stopAutoLightness();
                }
            }
        }
    }

    /**
     * 开启自动亮度
     */
    private void startAutoLightness() {
        SettingManager.getInstance().saveAutoBrightness(true);
        ScreenUtils.startAutoBrightness(ReaderActivity.this);
        seekbarLightness.setEnabled(false);
    }

    private void stopAutoLightness() {
        SettingManager.getInstance().saveAutoBrightness(false);
        ScreenUtils.stopAutoBrightness(ReaderActivity.this);
        int value = SettingManager.getInstance().getReadBrightness();
        seekbarLightness.setProgress(value);
        ScreenUtils.setScreenBrightness(value, ReaderActivity.this);
        seekbarLightness.setEnabled(true);
    }
}
