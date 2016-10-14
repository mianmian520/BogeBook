package com.boge.bogebook.module;


import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        fragment = fragment;
    }

    @Provides
    Fragment providesFragment() {
        return fragment;
    }

}
