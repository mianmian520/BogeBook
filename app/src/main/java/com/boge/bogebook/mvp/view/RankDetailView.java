package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * Created by boge on 2016/10/20.
 * 排行榜详细清单
 */

public interface RankDetailView extends BaseView {

    /**
     * 设置排行榜详细内容
     * @param rankings
     */
    void setRankings(Rankings rankings);

}
