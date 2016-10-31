package com.boge.bogebook.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.dbmanager.LARBManager;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.util.Tools;
import com.boge.entity.LocalAndRecomendBook;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public class RecommendAdapter extends RecyclerView.Adapter {
    private final static int NET_TYPE = 0;
    private final static int LOCAL_TYPE = 1;

    private List<LocalAndRecomendBook> recommendBooks;

    public void setRecommendBooks(List<LocalAndRecomendBook> recommendBooks) {
        this.recommendBooks = recommendBooks;
        notifyDataSetChanged();
    }

    public List<LocalAndRecomendBook> getRecommendBooks() {
        if(recommendBooks == null)return new ArrayList<LocalAndRecomendBook>();
        return recommendBooks;
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(recommendBooks.get(position).getBookId())) {
            return LOCAL_TYPE;
        } else {
            return NET_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        final RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRecyclerViewItemClick != null){
                    onRecyclerViewItemClick.onItemClick(view , recommendViewHolder.getLayoutPosition());
                    recommendViewHolder.iv_not_read.setVisibility(View.GONE);
                    recommendBooks.get(recommendViewHolder.getLayoutPosition()).setHasUp(false);
                    LARBManager.updateBook(recommendBooks.get(recommendViewHolder.getLayoutPosition()));
                }
            }
        });
        return recommendViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RecommendViewHolder){
            RecommendViewHolder viewHolder = (RecommendViewHolder) holder;
            if(recommendBooks.get(position).getLocal()){
                viewHolder.tvBookTitle.setText(recommendBooks.get(position).getTitle());
                viewHolder.tvLastChapter.setText(Tools.longToSize(recommendBooks.get(position).getSize()));
                viewHolder.ivTxtIcon.setImageResource(R.mipmap.home_shelf_txt_icon);
                viewHolder.iv_not_read.setVisibility(View.GONE);
            }else{
                viewHolder.tvBookTitle.setText(recommendBooks.get(position).getTitle());
                viewHolder.tvLastChapter.setText(recommendBooks.get(position).getLastChapter());
                Glide.with(BookApplication.getmContext())
                        .load(Constant.IMG_BASE_URL+recommendBooks.get(position).getCover())
                        .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .format(DecodeFormat.PREFER_RGB_565)
                        .into(viewHolder.ivTxtIcon);
                if(!recommendBooks.get(position).getHasUp()){
                    viewHolder.iv_not_read.setVisibility(View.GONE);
                } else {
                    viewHolder.iv_not_read.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return recommendBooks == null ? 0 : recommendBooks.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_txt_icon)
        ImageView ivTxtIcon;
        @Bind(R.id.tv_bookTitle)
        TextView tvBookTitle;
        @Bind(R.id.tv_lastChapter)
        TextView tvLastChapter;
        @Bind(R.id.iv_not_read)
        ImageView iv_not_read;
        @Bind(R.id.ck_boxSelect)
        CheckBox checkBox;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }
}
