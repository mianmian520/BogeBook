package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.RankingList;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * Created by boge on 2016/10/19.
 */

public interface RankingView extends BaseView {

    /**
     * 设置排行榜列表
     * @param rankingList
     */
    void setRankingList(RankingList rankingList);

}
