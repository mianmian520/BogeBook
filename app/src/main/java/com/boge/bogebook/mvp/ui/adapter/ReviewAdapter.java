package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/16
 */

public class ReviewAdapter extends BaseRecyclerViewAdapter {

    public ReviewAdapter(Context mContext, List mList, int... layoutIds) {
        super(mContext, mList, layoutIds);
    }

    @Override
    protected void onBindData(BaseViewHolder holder, int position, Object item) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
