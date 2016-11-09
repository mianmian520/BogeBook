package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.BookListTags;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/9
 */

public class TagRecyclerViewAdapter extends BaseRecyclerViewAdapter<BookListTags.DataBean> {

    private String tag;
    private OnBaseItemClick onBaseItemClick;

    public TagRecyclerViewAdapter(Context mContext, List<BookListTags.DataBean> mList, OnBaseItemClick onBaseItemClick) {
        this(mContext, mList, "", onBaseItemClick);
    }

    public TagRecyclerViewAdapter(Context mContext, List<BookListTags.DataBean> mList, String tag
                                  ,OnBaseItemClick onBaseItemClick ) {
        super(mContext, mList, R.layout.item_tags_list);
        this.tag = tag;
        this.onBaseItemClick = onBaseItemClick;
    }

    public void setTag(String tag) {
        this.tag = tag;
        notifyDataSetChanged();
    }

    @Override
    protected void onBindData(BaseViewHolder holder, int position, BookListTags.DataBean item) {
        holder.setText(R.id.tvTagGroupName , item.getName());
        RecyclerView rvTagsItem = holder.getView(R.id.rvTagsItem);
        rvTagsItem.setLayoutManager(new GridLayoutManager(mContext , 4));
        rvTagsItem.setHasFixedSize(true);
        final TagItemAdapter adapter = new TagItemAdapter(mContext, item.getTags());
        adapter.setOnItemClick(new OnBaseItemClick() {
            @Override
            public void onItemClick(View v, int position, Object data) {
                notifyDataSetChanged();
                onBaseItemClick.onItemClick(v, position, data);
            }
        });
        rvTagsItem.setAdapter(adapter);
    }

    class TagItemAdapter extends BaseRecyclerViewAdapter<String>{

        private OnBaseItemClick onItemClick;

        public TagItemAdapter(Context mContext, List<String> mList) {
            super(mContext, mList, R.layout.item_tag_text);
        }

        public void setOnItemClick(OnBaseItemClick onItemClick) {
            this.onItemClick = onItemClick;
        }

        @Override
        protected void onBindData(BaseViewHolder holder, final int position, final String item) {
            if(tag.equals(item)){
                holder.setTextColorRes(R.id.text, R.color.dark_red);
            }
            holder.setText(R.id.text, item);
            holder.setOnItemViewClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tag = item;
                    if(onItemClick != null){
                        onItemClick.onItemClick(view, position, item);
                    }
                }
            });
        }
    }
}
