package com.boge.bogebook.mvp.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.component.ActivityComponent;
import com.boge.bogebook.component.DaggerActivityComponent;
import com.boge.bogebook.module.ActivityModule;
import com.boge.bogebook.mvp.presenter.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    protected ActivityComponent activityComponent;

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initViews();

    protected BasePresenter basePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivityComponent();

        int layoutId = getLayoutId();
        setContentView(layoutId);

        initInjector();

        ButterKnife.bind(this);
        initViews();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((BookApplication) getApplication()).getmApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(basePresenter != null){
            basePresenter.onDestroy();
        }
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
}
