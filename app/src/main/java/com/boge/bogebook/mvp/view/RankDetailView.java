package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * Created by boge on 2016/10/20.
 */

public interface RankDetailView extends BaseView {

    void setRankings(Rankings rankings);

}
