package com.boge.bogebook.module;

import android.content.Context;

import com.boge.bogebook.BookApplication;

import dagger.Module;
import dagger.Provides;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

@Module
public class ApplicationModule {

    private BookApplication application;

    public ApplicationModule(BookApplication application) {
        this.application = application;
    }

    @Provides
    public Context providesApplicaitonContext(){
        return application.getApplicationContext();
    }
}
