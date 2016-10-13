package com.boge.bogebook.component;

import android.content.Context;

import com.boge.bogebook.module.ApplicationModule;

import dagger.Component;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getApplication();

}
