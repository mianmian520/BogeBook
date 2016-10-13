package com.boge.bogebook.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity providesActivity() {
        return mActivity;
    }

}
