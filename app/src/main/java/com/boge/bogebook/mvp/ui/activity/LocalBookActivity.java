package com.boge.bogebook.mvp.ui.activity;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boge.bogebook.R;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.presenter.impl.LocalBookPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.LocalBookAdapter;
import com.boge.bogebook.mvp.view.LocalBookView;
import com.boge.entity.LocalAndRecomendBook;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LocalBookActivity extends BaseActivity implements LocalBookView , OnBaseItemClick<LocalAndRecomendBook>{

    @Bind(R.id.tv_book_count)
    TextView tvBookCount;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private LocalBookAdapter localBookAdapter;

    @Inject
    LocalBookPresenterImpl localBookPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_local_book;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        localBookPresenter.attachView(this);
        localBookPresenter.searchLocalBook();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        localBookAdapter = new LocalBookAdapter(this ,localBookList , this );
        recyclerView.setAdapter(localBookAdapter);
    }

    @OnClick(R.id.btn_import_book)
    public void onClick() {
        final List<LocalAndRecomendBook> localAndRecomendBooks = localBookAdapter.getmList();
        final List<LocalAndRecomendBook> localAndRecomendBooks1 = new ArrayList<LocalAndRecomendBook>();
        for (LocalAndRecomendBook localAndRecomendBook : localAndRecomendBooks){
            if(localAndRecomendBook.isSelect()){
                localAndRecomendBooks1.add(localAndRecomendBook);
            }
        }
        if(localAndRecomendBooks1.size()>0){
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("是否加入书架")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EventBus.getDefault().post(localAndRecomendBooks1);
                            dialog.dismiss();
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }else{
            Snackbar.make(progressBar, "请选择要导入的书籍", Snackbar.LENGTH_SHORT).show();
        }
    }

    private List<LocalAndRecomendBook> localBookList = new ArrayList<LocalAndRecomendBook>();

    @Override
    public void setLocalBook(List<LocalAndRecomendBook> localBooks) {
        if(localBooks != null && localBooks.size()>0)localBookList.addAll(localBooks);
        if(localBookList.size() > 0){
            tvBookCount.setText("扫描到"+localBookList.size()+"个TXT文件");
        }else{
            tvBookCount.setText("没有扫描到TXT文件");
        }
        localBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(progressBar, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(View v, int position, LocalAndRecomendBook data) {
        data.setSelect(!data.isSelect());
        localBookAdapter.modify(localBookAdapter.getData(position) , data);
    }
}
