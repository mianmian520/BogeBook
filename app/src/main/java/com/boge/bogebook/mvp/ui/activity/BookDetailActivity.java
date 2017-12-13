package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookDetail;
import com.boge.bogebook.entity.HotReview;
import com.boge.bogebook.manager.dbmanager.LARBManager;
import com.boge.bogebook.mvp.presenter.impl.BookDetailPresenterImpl;
import com.boge.bogebook.mvp.presenter.impl.ReviewPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.TagAdapter;
import com.boge.bogebook.mvp.view.BookDetailView;
import com.boge.bogebook.util.Tools;
import com.boge.bogebook.view.DrawableCenterButton;
import com.boge.bogebook.view.FullyLinearLayoutManager;
import com.boge.bogebook.view.TagGroup;
import com.boge.entity.LocalAndRecomendBook;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Bind(R.id.tv_read_count)
    TextView tvReadCount;
    @Bind(R.id.tv_reader_retained)
    TextView tvReaderRetained;
    @Bind(R.id.tv_day_update_count)
    TextView tvDayUpdateCount;
    @Bind(R.id.view_line)
    View viewLine;
    @Bind(R.id.rv_review)
    RecyclerView rv_review;

    private TagAdapter<String> mClickAdapter;

    private LocalAndRecomendBook book;
    private boolean isLocal;

    private String author;

    @Inject
    BookDetailPresenterImpl bookDetailPresenter;

    @Inject
    ReviewPresenterImpl reviewPresenter;

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
        basePresenter = bookDetailPresenter;
        bookDetailPresenter.attachView(this);
        bookDetailPresenter.loadBookDetail(getIntent().getStringExtra("bookId"));
        mClickAdapter = new TagAdapter<>(this);
        mClickAdapter.setOnTagItemClick(new TagAdapter.OnTagItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                List<String> strings = mClickAdapter.getmDataList();
                Intent intent = new Intent(BookDetailActivity.this, AuthorActivity.class);
                intent.putExtra("tags", strings.get(position));
                startActivity(intent);
            }
        });
        tvTag.setAdapter(mClickAdapter);

        reviewPresenter.attachView(this);
        reviewPresenter.loadBookHotReview(getIntent().getStringExtra("bookId"));
        initRecycler();
    }

    private void initRecycler() {
        rv_review.setLayoutManager(new FullyLinearLayoutManager(this));
        rv_review.setHasFixedSize(true);
    }

    private boolean isOpen = false;

    @OnClick({R.id.tv_longIntro, R.id.btn_update, R.id.btn_start_read ,R.id.tv_author})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_longIntro:
                if (isOpen) {
                    tvLongIntro.setMaxLines(4);
                } else {
                    tvLongIntro.setMaxLines(tvLongIntro.getLineCount());
                }
                isOpen = !isOpen;
                break;
            case R.id.btn_update:
                addAndDeleteLocal();
                break;
            case R.id.btn_start_read:
                startRead();
                break;
            case R.id.tv_author:
                Intent intent = new Intent(this, AuthorActivity.class);
                intent.putExtra("author", author);
                startActivity(intent);
                break;
        }
    }

    /**
     * 添加和移除     到本地
     */
    private void addAndDeleteLocal(){
        if(isLocal){
            EventBus.getDefault().post(book);
            setBtnUpdateStyle(getResources().getString(R.string.update),
                    R.color.red,
                    getResources().getDrawable(R.mipmap.book_detail_info_add_img));
            showErrorMsg(getResources().getString(R.string.remove_book)+"《"+book.getTitle()+"》");
        }else {
            List<LocalAndRecomendBook> list = new ArrayList<LocalAndRecomendBook>();
            list.add(book);
            EventBus.getDefault().post(list);
            setBtnUpdateStyle(getResources().getString(R.string.no_chase),
                    R.color.common_h3,
                    getResources().getDrawable(R.mipmap.book_detail_info_del_img));
            showErrorMsg(getResources().getString(R.string.add_book)+"《"+book.getTitle()+"》");
        }
        isLocal = !isLocal;
    }

    /**
     * 开始阅读
     */
    private void startRead(){
        Intent intent = new Intent(this , ReaderActivity.class);
        if(book.getIsLocal()){
            intent.putExtra(Constant.PATH , book.getPath());
        } else {
            intent.putExtra(Constant.PATH , book.getBookId());
        }
        intent.putExtra(Constant.TITLE , book.getTitle());
        intent.putExtra(Constant.LOCAL , book.getIsLocal());
        startActivity(intent);
    }

    /**
     * 得到书籍详细信息，给页面控件赋值
     * @param bookDetail
     */
    @Override
    public void setBookDetail(BookDetail bookDetail) {
        if (bookDetail != null) {
            Glide.with(BookApplication.getmContext()).load(Constant.IMG_BASE_URL + bookDetail.getCover())
                    .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.mipmap.cover_default)
                    .into(ivBookIcon);
            author = bookDetail.getAuthor();
            isBookExist(bookDetail.get_id());
            if(book == null){
                book = new LocalAndRecomendBook();
                book.setBookId(bookDetail.get_id());
                book.setCover(bookDetail.getCover());
                book.setTitle(bookDetail.getTitle());
                book.setIsTop(false);
                book.setLastChapter(bookDetail.getLastChapter());
                book.setHasUp(false);
                book.setIsLocal(false);
            }
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
    public void setBookHotReview(HotReview hotReview) {
        if(hotReview != null){
            Log.i("test", hotReview.toString());
        }
    }

    /**
     * 查看这本书是否在本地，设置按钮样式
     */
    private void isBookExist(String bookId) {
        book = LARBManager.getBook(bookId);
        if(book != null){
            setBtnUpdateStyle(getResources().getString(R.string.no_chase),
                    R.color.common_h3,
                    getResources().getDrawable(R.mipmap.book_detail_info_del_img));
            isLocal = true;
        }else {
            isLocal = false;
        }

    }

    private void setBtnUpdateStyle(String text, int color, Drawable drawable){
        btnUpdate.setText(text);
        btnUpdate.setBackgroundResource(color);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        btnUpdate.setCompoundDrawables(drawable, null, null, null);
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
