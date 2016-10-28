package com.boge.bogebook.listener;

import android.view.View;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public interface OnBaseItemClick<T> {


    void onItemClick(View v , int position , T data);
}
