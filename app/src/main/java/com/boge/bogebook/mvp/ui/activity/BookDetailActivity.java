package com.boge.bogebook.mvp.ui.activity;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.mvp.presenter.impl.BookDetailPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.TagAdapter;
import com.boge.bogebook.mvp.view.BookDetailView;
import com.boge.bogebook.util.Tools;
import com.boge.bogebook.view.DrawableCenterButton;
import com.boge.bogebook.view.TagGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements BookDetailView {

    @Bind(R.id.tv_tag)
    TagGroup tvTag;
    @Bind(R.id.tv_longIntro)
    TextView tvLongIntro;
    @Bind(R.id.iv_book_icon)
    ImageView ivBookIcon;
    @Bind(R.id.tv_book_title)
    TextView tvBookTitle;
    @Bind(R.id.tv_author)
    TextView tvAuthor;
    @Bind(R.id.tv_cat)
    TextView tvCat;
    @Bind(R.id.tv_word_count)
    TextView tvWordCount;
    @Bind(R.id.tv_lately_update)
    TextView tvLatelyUpdate;
    @Bind(R.id.btn_update)
    DrawableCenterButton btnUpdate;
    @Bind(R.id.btn_start_read)
    DrawableCenterButton btnStartRead;
    @Bind(R.id.tv_read_count)
    TextView tvReadCount;
    @Bind(R.id.tv_reader_retained)
    TextView tvReaderRetained;
    @Bind(R.id.tv_day_update_count)
    TextView tvDayUpdateCount;
    @Bind(R.id.view_line)
    View viewLine;

    private TagAdapter<String> mClickAdapter;

    @Inject
    BookDetailPresenterImpl bookDetailPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        bookDetailPresenter.attachView(this);
        bookDetailPresenter.loadBookDetail(getIntent().getStringExtra("bookId"));
        mClickAdapter = new TagAdapter<>(this);
        tvTag.setAdapter(mClickAdapter);
    }

    private boolean isOpen = false;

    @OnClick(R.id.tv_longIntro)
    public void onClick() {
        if (isOpen) {
            tvLongIntro.setMaxLines(4);
        } else {
            tvLongIntro.setMaxLines(tvLongIntro.getLineCount());
        }
        isOpen = !isOpen;
    }

    @Override
    public void setBookDetail(BookDetail bookDetail) {
        if (bookDetail != null) {
            Glide.with(BookApplication.getmContext()).load(Constant.IMG_BASE_URL + bookDetail.getCover())
                    .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.mipmap.cover_default)
                    .into(ivBookIcon);
            tvAuthor.setText(bookDetail.getAuthor());
            tvCat.setText("|  " + bookDetail.getCat());
            tvBookTitle.setText(bookDetail.getTitle());
            if (bookDetail.getWordCount() != 0) {
                tvWordCount.setText("|  " + Tools.intToStr(bookDetail.getWordCount()) + "字");
            }

            if (bookDetail.isIsSerial()) {
                String updated = bookDetail.getUpdated();
                updated = updated.substring(0, updated.indexOf(".")).replace("T", " ");
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updated);
                    Date date1 = new Date();
                    long l = date1.getTime() - date.getTime();
                    tvLatelyUpdate.setText(Tools.longToDateString(l));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                tvLatelyUpdate.setText("完结");
            }

            tvReadCount.setText(bookDetail.getLatelyFollower() + "");
            if (bookDetail.getRetentionRatio() == null) {
                tvReaderRetained.setText("-");
            } else {
                tvReaderRetained.setText(bookDetail.getRetentionRatio() + "%");
            }
            if (bookDetail.getSerializeWordCount() == -1) {
                tvDayUpdateCount.setText("-");
            } else {
                tvDayUpdateCount.setText(bookDetail.getSerializeWordCount() + "");
            }
            tvLongIntro.setText(bookDetail.getLongIntro());
            if (bookDetail.getTags() != null && bookDetail.getTags().size() > 0) {
                mClickAdapter.onlyAddAll(bookDetail.getTags());
            } else {
                tvTag.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showProgress() {
        /*progressBar.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void hideProgress() {
        /*progressBar.setVisibility(View.GONE);*/
    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(ivBookIcon, message, Snackbar.LENGTH_SHORT).show();
    }

}
