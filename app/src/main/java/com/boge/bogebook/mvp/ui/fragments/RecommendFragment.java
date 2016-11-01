package com.boge.bogebook.mvp.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.dbmanager.LARBManager;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.listener.OnRecyclerViewLongItemClick;
import com.boge.bogebook.mvp.presenter.impl.RecommendPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.BookDetailActivity;
import com.boge.bogebook.mvp.ui.activity.ReaderActivity;
import com.boge.bogebook.mvp.ui.adapter.RecommendAdapter;
import com.boge.bogebook.mvp.ui.fragments.base.BaseFragment;
import com.boge.bogebook.mvp.view.RecommendView;
import com.boge.entity.LocalAndRecomendBook;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class RecommendFragment extends BaseFragment implements RecommendView
        , OnRecyclerViewItemClick, SwipeRefreshLayout.OnRefreshListener
        , OnRecyclerViewLongItemClick {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.btn_check_all)
    Button btnCheckAll;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.ll_operation)
    LinearLayout llOperation;

    private RecommendAdapter adapter;

    private String gender;

    private List<LocalAndRecomendBook> books;
    private boolean isBatch = false;

    @Inject
    RecommendPresenterImpl recommendPresenter;

    public static RecommendFragment newInstance(String param1) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(Constant.GENDER, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gender = getArguments().getString(Constant.GENDER);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(List<LocalAndRecomendBook> localAndRecomendBooks) {
        recommendPresenter.addBookcase(localAndRecomendBooks);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initDatas() {
        recommendPresenter.attachView(this);
        recommendPresenter.loadRecommendBook(gender);
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new RecommendAdapter();
        adapter.setOnRecyclerViewItemClick(this);
        adapter.setOnRecyclerViewLongItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化SwipeRefreshLayout
     */
    private void initSwipeRefreshLayout() {
        //下拉监听
        swipeRefreshLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色,一种颜色转一圈 循环
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipeRefreshLayout.setDistanceToTriggerSync(400);
        //进度圈的背景色
        swipeRefreshLayout.setProgressBackgroundColor(android.R.color.white);
        //设置进度圈的大小，只有两个值：DEFAULT、LARGE
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void setReCommendBook(List<LocalAndRecomendBook> recommendBookList) {
        if (recommendBookList != null) {//推荐的是否为空
            adapter.setRecommendBooks(recommendBookList);
            books = recommendBookList;
        } else {
            books = new ArrayList<LocalAndRecomendBook>();
        }
    }

    @Override
    public void setBookUpdateInfo(List<LocalAndRecomendBook> localAndRecomendBooks) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        for (LocalAndRecomendBook book : localAndRecomendBooks) {
            for (LocalAndRecomendBook recomendBook : books) {
                if (recomendBook != null && recomendBook.getBookId().equals(book.getBookId())) {
                    recomendBook = book;
                    break;
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addBookCase(List<LocalAndRecomendBook> books) {
        this.books.addAll(books);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View v, int position) {
        if(isBatch){
            List<LocalAndRecomendBook> choiceBook = adapter.getChoiceBook();
            if(choiceBook.size() > 0){
                btnDelete.setText("删除("+choiceBook.size()+")");
            }else{
                btnDelete.setText("删除");
            }
            if(choiceBook.size() == books.size()){
                btnCheckAll.setText("取消全选");
            } else {
                ischoice = true;
                btnCheckAll.setText("全选");
            }
        }else{
            //Snackbar.make(recyclerView, adapter.getRecommendBooks().get(position).toString(), Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity() , ReaderActivity.class);
            if(books.get(position).getIsLocal()){
                intent.putExtra(Constant.PATH , books.get(position).getPath());
            }else{
                intent.putExtra(Constant.PATH , books.get(position).getBookId());
            }
            intent.putExtra(Constant.LOCAL , books.get(position).getIsLocal());
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        if(llOperation.getVisibility() != View.VISIBLE){
            recommendPresenter.loadUpdateInfo();
        }else{
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLongItemClcik(View view, int position) {
        showLongClickDialog(position);
    }

    private void showLongClickDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(books.get(position).getTitle());
        if (books.get(position).getIsLocal()) {
            builder.setItems(getResources().getStringArray(R.array.local_item_long_click_choice)
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case 0:
                                    bookStickied(position);
                                    break;
                                case 1:
                                    List<Integer> ps = new ArrayList<Integer>();ps.add(position);
                                    delete(ps);
                                    break;
                                case 2:
                                    showBatchManagementLayout();
                                    break;
                            }
                        }
                    }).create().show();
        } else {
            builder.setItems(getResources().getStringArray(R.array.recommend_item_long_click_choice)
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case 0:
                                    bookStickied(position);
                                    break;
                                case 1:
                                    Intent intent = new Intent(getActivity() , BookDetailActivity.class);
                                    intent.putExtra("bookId",books.get(position).getBookId());
                                    startActivity(intent);
                                    break;
                                case 2:
                                    Toast.makeText(getActivity() , "正在拼命开发中。。。" ,Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Toast.makeText(getActivity() , "正在拼命开发中。。。" ,Toast.LENGTH_SHORT).show();
                                    break;
                                case 4:
                                    List<Integer> ps = new ArrayList<Integer>();ps.add(position);
                                    delete(ps);
                                    break;
                                case 5:
                                    showBatchManagementLayout();
                                    break;
                            }
                        }
                    }).create().show();
        }
    }

    /**
     * 书籍置顶
     * @param position
     */
    private void bookStickied(int position) {
        LocalAndRecomendBook book = books.get(position);
        if(!book.getIsTop()){
            book.setIsTop(true);
            recommendPresenter.bookStickied(book);
            books.remove(position);
            books.add(0,book);
            adapter.notifyDataSetChanged();
        }
    }

    private void goneBatchManagementLayout(){
        llOperation.setVisibility(View.GONE);
    }

    private void showBatchManagementLayout() {
        llOperation.setVisibility(View.VISIBLE);
        adapter.setBatch((isBatch = true));
    }

    private void delete(final List<Integer> position) {
        new AlertDialog.Builder(getActivity()).setTitle("移除所选书籍")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int k = position.size()-1 ; k >= 0 ; k--){
                            LARBManager.deleteBook(books.get(position.get(k)));
                            books.remove((int)position.get(k));
                        }
                        adapter.notifyDataSetChanged();
                        btnDelete.setText("删除");btnCheckAll.setText("全选");ischoice = true;
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("取消", null).create().show();

    }
    private boolean ischoice = true;
    @OnClick({R.id.btn_check_all, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_all:
                adapter.setchoiceAll(ischoice);
                if(ischoice){
                    ischoice = false;
                    btnDelete.setText("删除("+adapter.getChoiceBook().size()+")");
                }else{
                    ischoice = true;
                    btnDelete.setText("删除");
                }
                break;
            case R.id.btn_delete:
                if(adapter.getChoiceBook().size()==0){
                    Toast.makeText(getActivity() , "请选择所要移除的读书" , Toast.LENGTH_SHORT).show();
                }else{
                    delete(adapter.getPosition());
                }
                break;
        }
    }

    /**
     * 点击返回键时查看是否在批量管理
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isBatch){
            goneBatchManagementLayout();
            adapter.setBatch((isBatch = false));
            return false;
        }
        return true;
    }
}
