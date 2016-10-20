package com.boge.bogebook.component;

import com.boge.bogebook.mvp.ui.fragments.FindFragment;
import com.boge.bogebook.mvp.ui.fragments.RankDetailFragment;
import com.boge.bogebook.mvp.ui.fragments.RecommendFragment;

import dagger.Component;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

@Component(dependencies = ApplicationComponent.class )
public interface FragmentComponent {

    void inject(RecommendFragment fragment);

    void inject(FindFragment fragment);

    void inject(RankDetailFragment fragment);

}
