package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/3
 */

public class ImgAndTextAdapter extends BaseRecyclerViewAdapter<String> {
    private OnBaseItemClick onBaseItemClick;
    private int imgId;

    public ImgAndTextAdapter(Context mContext, List<String> mList, int imgId, OnBaseItemClick itemClick) {
        super(mContext, mList, R.layout.item_img_text);
        onBaseItemClick = itemClick;
        this.imgId = imgId;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final int position, final String item) {
        holder.setText(R.id.tv_key_book , item)
                .setImageResource(R.id.iv_image , imgId);
        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBaseItemClick.onItemClick(view , position , item);
            }
        });
    }
}
