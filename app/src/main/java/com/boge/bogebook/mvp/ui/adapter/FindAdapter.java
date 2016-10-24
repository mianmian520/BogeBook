package com.boge.bogebook.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.support.FindBean;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/17
 */

public class FindAdapter extends RecyclerView.Adapter {


    private List<FindBean> findBeanList;

    public FindAdapter(List<FindBean> findBeanList) {
        this.findBeanList = findBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, parent, false);
        final FindViewHolder viewHolder = new FindViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRecyclerViewItemClick != null){
                    onRecyclerViewItemClick.onItemClick(view , viewHolder.getLayoutPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FindViewHolder viewHolder = (FindViewHolder) holder;

        viewHolder.ivIcon.setImageResource(findBeanList.get(position).getIconResId());
        viewHolder.tvTitle.setText(findBeanList.get(position).getTitle());
    }

    class FindViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivIcon)
        ImageView ivIcon;
        @Bind(R.id.tvTitle)
        TextView tvTitle;

        public FindViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

    @Override
    public int getItemCount() {
        return findBeanList == null ? 0 : findBeanList.size();
    }



    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }
}
