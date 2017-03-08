package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.mvp.presenter.impl.AuthorPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.BookListDetailAdapter;
import com.boge.bogebook.mvp.view.AuthorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class AuthorActivity extends BaseActivity implements AuthorView {

    @Bind(R.id.rv_book)
    RecyclerView rvBook;

    @Inject
    AuthorPresenterImpl authorPresenter;

    /***书籍详细列表适配器*/
    private BookListDetailAdapter bookAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_author;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        String author = getIntent().getStringExtra("author");
        toolbar.setTitle(author);

        initBookRecyclerView();

        basePresenter = authorPresenter;
        authorPresenter.attachView(this);

        authorPresenter.loadAuthorToBook(author);
    }

    private void initBookRecyclerView() {
        rvBook.setLayoutManager(new LinearLayoutManager(this));
        rvBook.setHasFixedSize(true);
        bookAdapter = new BookListDetailAdapter();
        bookAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(AuthorActivity.this, BookDetailActivity.class);
                intent.putExtra("bookId" , bookAdapter.getBooksBeen().get(position).get_id());
                startActivity(intent);
            }
        });
        rvBook.setAdapter(bookAdapter);
    }


    @Override
    public void setBooks(List<BookInfo> bookInfos) {
        if(bookInfos != null && bookInfos.size() > 0){
            bookAdapter.setBooksBeen(bookInfos);
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
        Snackbar.make(rvBook, message, Snackbar.LENGTH_SHORT).show();
    }

}
