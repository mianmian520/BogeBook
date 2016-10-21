package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public interface CategoryView extends BaseView {

    /**
     * 设置一级分类
     * @param categoryList
     */
    void setCategoryList(CategoryList categoryList);

}
