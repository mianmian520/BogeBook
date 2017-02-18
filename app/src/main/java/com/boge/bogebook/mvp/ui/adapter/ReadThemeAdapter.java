package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.manager.ThemeManager;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2017/2/17
 */

public class ReadThemeAdapter extends BaseRecyclerViewAdapter<Integer> {

    private OnBaseItemClick onBaseItemClick;
    private int selected = 0;

    public ReadThemeAdapter(Context mContext, List<Integer> mList, int select, OnBaseItemClick itemClick) {
        super(mContext, mList, R.layout.item_read_theme);
        this.selected = select;
        onBaseItemClick = itemClick;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final int position, final Integer item) {
        ThemeManager.setReaderTheme(item, holder.getView(R.id.ivThemeBg));

        if (selected == position) {
            holder.setVisibility(R.id.ivSelected, View.VISIBLE);
        } else {
            holder.setVisibility(R.id.ivSelected, View.GONE);
        }

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBaseItemClick.onItemClick(view , position , item);
            }
        });
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }
}
