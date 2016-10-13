package com.boge.bogebook.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.component.ActivityComponent;
import com.boge.bogebook.component.DaggerActivityComponent;
import com.boge.bogebook.module.ActivityModule;

import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivityComponent();

        int layoutId = getLayoutId();
        setContentView(layoutId);

        initInjector();

        ButterKnife.bind(this);
        initViews();

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
}
