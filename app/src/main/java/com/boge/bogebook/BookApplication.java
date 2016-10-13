package com.boge.bogebook;

import android.app.Application;

import com.boge.bogebook.component.ApplicationComponent;
import com.boge.bogebook.component.DaggerApplicationComponent;
import com.boge.bogebook.module.ApplicationModule;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

public class BookApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getmApplicationComponent() {
        return mApplicationComponent;
    }
}
