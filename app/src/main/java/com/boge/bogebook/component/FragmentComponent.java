package com.boge.bogebook.component;

import com.boge.bogebook.mvp.ui.fragments.BookFragment;

import dagger.Component;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

@Component(dependencies = ApplicationComponent.class )
public interface FragmentComponent {

    void inject(BookFragment fragment);

}
