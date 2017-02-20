package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.BookToc;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2017/2/20
 */

public class ChapterAdapter extends BaseRecyclerViewAdapter<BookToc.ChaptersBean> {

    private OnBaseItemClick onBaseItemClick;
    private int index = 0;

    public ChapterAdapter(Context mContext, List<BookToc.ChaptersBean> mList, int index, OnBaseItemClick itemClick) {
        super(mContext, mList, R.layout.item_chapter);
        this.index = index;
        this.onBaseItemClick = itemClick;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final int position, final BookToc.ChaptersBean item) {
        holder.setText(R.id.tv_chapter, item.getTitle());
        if(index == position){
            holder.setTextColorRes(R.id.tv_chapter, R.color.red);
        }else {
            holder.setTextColorRes(R.id.tv_chapter, R.color.black);
        }

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onBaseItemClick != null){
                    onBaseItemClick.onItemClick(view , position , item);
                }
            }
        });
    }
}
