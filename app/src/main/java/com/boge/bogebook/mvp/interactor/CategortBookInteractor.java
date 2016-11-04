package com.boge.bogebook.mvp.interactor;

import com.boge.bogebook.common.Constant;
import com.boge.bogebook.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/4
 */

public interface CategortBookInteractor {

    /**
     * 获取二级分类
     */
    Subscription loadCategortLv2(RequestCallBack callBack);


    /**
     * 按分类获取书籍列表
     *
     * @param gender male、female、press
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param start  开始下标
     * @param limit  结束下标   分页
     * @return
     */
    Subscription loadBookInfos(String gender, @Constant.CateType String type, String major, String minor, int start, int limit, RequestCallBack callBack);
}
