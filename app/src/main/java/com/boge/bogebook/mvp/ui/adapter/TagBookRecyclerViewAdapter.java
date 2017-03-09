package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.support.BookInfo;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2017/3/8
 */

public class TagBookRecyclerViewAdapter extends BaseRecyclerViewAdapter<BookInfo>{

    private OnBaseItemClick onBaseItemClick;

    public TagBookRecyclerViewAdapter(Context mContext, List<BookInfo> mList, OnBaseItemClick onBaseItemClick) {
        super(mContext, mList, R.layout.item_tag_book);
        this.onBaseItemClick = onBaseItemClick;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final int position, final BookInfo item) {
        String cat = item.getCat();
        cat = cat.replace("\"", "").replace(",", "|");
        cat = cat.substring(1, cat.length()-1);
        holder.setText(R.id.tv_book_title, item.getTitle())
                .setText(R.id.tv_shortIntro, item.getShortIntro())
                .setImageUrl(R.id.iv_book_icon, Constant.IMG_BASE_URL+item.getCover())
                .setText(R.id.tv_tags, cat);
        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onBaseItemClick != null){
                    onBaseItemClick.onItemClick(v, position, item);
                }
            }
        });
    }
}
