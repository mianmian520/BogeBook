package com.boge.bogebook.mvp.view;

import com.boge.bogebook.entity.BooksByCats;
import com.boge.bogebook.entity.CategoryListLv2;
import com.boge.bogebook.mvp.view.base.BaseView;

/**
 * Created by boge on 2016/10/26.
 */

public interface CategortBookView extends BaseView {

    void setCategoryListLv2(CategoryListLv2 categoryListLv2);

    void setBooksByCats(BooksByCats booksByCats);

}
