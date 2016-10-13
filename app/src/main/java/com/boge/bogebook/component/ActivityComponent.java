package com.boge.bogebook.component;

import com.boge.bogebook.module.ActivityModule;
import com.boge.bogebook.ui.activity.MainActivity;

import dagger.Component;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/13
 */

@Component(dependencies = ApplicationComponent.class , modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

}
