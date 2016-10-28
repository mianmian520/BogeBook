package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.boge.bogebook.R;
import com.boge.bogebook.bean.LocalAndRecomendBook;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;
import com.boge.bogebook.util.Tools;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class LocalBookAdapter extends BaseRecyclerViewAdapter<LocalAndRecomendBook> {
    private OnBaseItemClick onBaseItemClick;

    public LocalBookAdapter(Context mContext, List mList , OnBaseItemClick itemClick) {
        super(mContext, mList, R.layout.item_recommend);
        onBaseItemClick = itemClick;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final int position, final LocalAndRecomendBook item) {
        holder.setImageResource(R.id.iv_txt_icon,R.mipmap.home_shelf_txt_icon)
                .setText(R.id.tv_bookTitle,item.getName())
                .setText(R.id.tv_lastChapter, Tools.longToSize(item.getSize()))
                .setVisibility(R.id.iv_not_read , View.GONE)
                .setVisibility(R.id.ck_boxSelect , View.VISIBLE)
                .setChecked(R.id.ck_boxSelect , item.isSelect());
        CheckBox ckBoxSelect = holder.getView(R.id.ck_boxSelect);
        ckBoxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setSelect(b);
            }
        });
        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBaseItemClick.onItemClick(view , position , item);
            }
        });
    }

}
