package com.boge.bogebook.mvp.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.mvp.presenter.impl.CategoryPresenterImpl;
import com.boge.bogebook.mvp.ui.activity.base.BaseActivity;
import com.boge.bogebook.mvp.ui.adapter.CategoryTopAdapter;
import com.boge.bogebook.mvp.view.CategoryView;
import com.boge.bogebook.view.SupportGridItemDecoration;

import javax.inject.Inject;

import butterknife.Bind;

public class CategoryActivity extends BaseActivity implements CategoryView
                    ,CategoryTopAdapter.OnRecyclerViewItemClick {

    private static final int MALE = 1;
    private static final int FEMALE = 2;
    private static final int PRESS = 3;

    @Bind(R.id.rv_male_category)
    RecyclerView rvMaleCategory;
    @Bind(R.id.rv_female_category)
    RecyclerView rvFemaleCategory;
    @Bind(R.id.rv_press_category)
    RecyclerView rvPressCategory;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private CategoryTopAdapter maleAdapter;
    private CategoryTopAdapter femaleAdapter;
    private CategoryTopAdapter pressAdapter;

    @Inject
    CategoryPresenterImpl categoryPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected void initInjector() {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews() {
        basePresenter = categoryPresenter;
        categoryPresenter.attachView(this);
        categoryPresenter.loadCategoryList();
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvMaleCategory.setHasFixedSize(true);
        rvMaleCategory.setLayoutManager(new GridLayoutManager(this , 3));
        rvMaleCategory.addItemDecoration(new SupportGridItemDecoration(this));
        maleAdapter = new CategoryTopAdapter();
        rvMaleCategory.setAdapter(maleAdapter);
        maleAdapter.setOnRecyclerViewItemClick(this , MALE);

        rvFemaleCategory.setHasFixedSize(true);
        rvFemaleCategory.setLayoutManager(new GridLayoutManager(this , 3));
        rvFemaleCategory.addItemDecoration(new SupportGridItemDecoration(this));
        femaleAdapter = new CategoryTopAdapter();
        rvFemaleCategory.setAdapter(femaleAdapter);
        femaleAdapter.setOnRecyclerViewItemClick(this , FEMALE);

        rvPressCategory.setHasFixedSize(true);
        rvPressCategory.setLayoutManager(new GridLayoutManager(this , 3));
        rvPressCategory.addItemDecoration(new SupportGridItemDecoration(this));
        pressAdapter = new CategoryTopAdapter();
        rvPressCategory.setAdapter(pressAdapter);
        pressAdapter.setOnRecyclerViewItemClick(this , PRESS);
    }

    @Override
    public void setCategoryList(CategoryList categoryList) {
        if(categoryList != null){
            maleAdapter.setMaleBeens(categoryList.getMale());
            femaleAdapter.setMaleBeens(categoryList.getFemale());
            pressAdapter.setMaleBeens(categoryList.getPress());
        }
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
    public void onItemClick(View v, int position , int flag) {
        CategoryList.MaleBean maleBean = null;
        String gender = "";
        switch (flag){
            case MALE:
                maleBean = maleAdapter.getMaleBeens().get(position);
                gender = Constant.MALE;
                break;
            case FEMALE:
                maleBean = femaleAdapter.getMaleBeens().get(position);
                gender = Constant.FEMALE;
                break;
            case PRESS:
                maleBean = pressAdapter.getMaleBeens().get(position);
                gender = Constant.PRESS;
                break;
        }
        Intent intent = new Intent(this, CategortBookActivity.class);
        intent.putExtra("maleBean" , maleBean);
        intent.putExtra(Constant.GENDER , gender);
        startActivity(intent);
    }
}
