package com.boge.bogebook.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boge.bogebook.R;
import com.boge.bogebook.entity.CategoryList;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class CategoryTopAdapter extends RecyclerView.Adapter {

    private List<CategoryList.MaleBean> maleBeens;

    public void setMaleBeens(List<CategoryList.MaleBean> maleBeens) {
        this.maleBeens = maleBeens;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        final CategoryViewHolder viewHolder = new CategoryViewHolder(view);
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
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.tvCategoryName.setText(maleBeens.get(position).getName());
        viewHolder.tvCategoryCount.setText(maleBeens.get(position).getBookCount()+"本");
    }

    @Override
    public int getItemCount() {
        return maleBeens == null ? 0 : maleBeens.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_category_name)
        TextView tvCategoryName;
        @Bind(R.id.tv_category_count)
        TextView tvCategoryCount;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

}
