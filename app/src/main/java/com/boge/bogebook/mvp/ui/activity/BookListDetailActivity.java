package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookListDetail;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.presenter.impl.BookListDetailPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListInfoAdapter;
import com.boge.bogebook.mvp.view.BookListDetailView;
import com.boge.bogebook.util.Tools;
import com.boge.bogebook.view.CircleOrRectImage;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class BookListDetailActivity extends BaseActivity implements BookListDetailView {

    @Bind(R.id.iv_author)
    CircleOrRectImage ivAuthor;
    @Bind(R.id.tv_book_list_author)
    TextView tvBookListAuthor;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.iv_author_1)
    CircleOrRectImage ivAuthor1;
    @Bind(R.id.tv_author)
    TextView tvAuthor;
    @Bind(R.id.book_recyclerView)
    RecyclerView bookRecyclerView;

    private String bookListId;

    private BookListInfoAdapter adapter;

    @Inject
    BookListDetailPresenterImpl bookListDetailPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_list_detail;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        bookListId = getIntent().getStringExtra(Constant.BOOKLIST_ID);

        basePresenter = bookListDetailPresenter;
        bookListDetailPresenter.attachView(this);
        bookListDetailPresenter.loadBookListDetail(bookListId);

        initBookRecyclerView();
    }

    private void initBookRecyclerView() {
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookRecyclerView.setHasFixedSize(true);
        adapter = new BookListInfoAdapter(this, null);
        adapter.setOnBaseItemClick(new OnBaseItemClick<BookListDetail.BookListBean.BooksBean>() {
            @Override
            public void onItemClick(View v, int position, BookListDetail.BookListBean.BooksBean data) {
                Intent intent = new Intent(BookListDetailActivity.this, BookDetailActivity.class);
                intent.putExtra("bookId", data.getBook().get_id());
                startActivity(intent);
            }
        });
        bookRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.ll_share)
    public void onClick() {
    }

    @Override
    public void setBookListDetail(BookListDetail.BookListBean bookListDetail) {
        setBookInfo(bookListDetail);
        adapter.setmList(bookListDetail.getBooks());
    }

    private void setBookInfo(BookListDetail.BookListBean bookListDetail) {
        Glide.with(this).load(Constant.IMG_BASE_URL+bookListDetail.getAuthor().getAvatar())
                .asBitmap().into(ivAuthor);
        Glide.with(this).load(Constant.IMG_BASE_URL+bookListDetail.getAuthor().getAvatar())
                .asBitmap().into(ivAuthor1);
        tvBookListAuthor.setText(bookListDetail.getAuthor().getNickname()+"  lv."+
                bookListDetail.getAuthor().getLv());

        String updated = bookListDetail.getUpdated();
        updated = updated.substring(0, updated.indexOf(".")).replace("T", " ");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updated);
            Date date1 = new Date();
            long l = date1.getTime() - date.getTime();
            tvDate.setText(Tools.longToDateString(l));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTitle.setText(bookListDetail.getTitle());
        tvDesc.setText(bookListDetail.getDesc());
        tvAuthor.setText("创建自"+bookListDetail.getAuthor().getNickname());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(bookRecyclerView, message, Snackbar.LENGTH_SHORT).show();
    }
}
