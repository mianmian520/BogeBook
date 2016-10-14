package com.boge.bogebook.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.Recommend;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

    private List<Recommend.RecommendBook> recommendBooks;

    public void setRecommendBooks(List<Recommend.RecommendBook> recommendBooks) {
        this.recommendBooks = recommendBooks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(recommendBooks.get(position).get_id())) {
            return LOCAL_TYPE;
        } else {
            return NET_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend, parent, false);
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
        return recommendViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RecommendViewHolder){
            RecommendViewHolder viewHolder = (RecommendViewHolder) holder;
            viewHolder.tvBookTitle.setText(recommendBooks.get(position).getTitle());
            viewHolder.tvLastChapter.setText(recommendBooks.get(position).getLastChapter());
            Glide.with(BookApplication.getmContext())
                    .load(Constant.IMG_BASE_URL+recommendBooks.get(position).getCover())
                    .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .into(viewHolder.ivTxtIcon);
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

        public RecommendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

}
