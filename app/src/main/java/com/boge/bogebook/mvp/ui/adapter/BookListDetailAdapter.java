package com.boge.bogebook.mvp.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.Rankings;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;
import com.boge.bogebook.util.Tools;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 * 书籍详细列表适配器
 */

public class BookListDetailAdapter extends RecyclerView.Adapter {

    private List<Rankings.RankingBean.BooksBean> booksBeen;

    public void setBooksBeen(List<Rankings.RankingBean.BooksBean> booksBeen) {
        this.booksBeen = booksBeen;
        notifyDataSetChanged();
    }

    public List<Rankings.RankingBean.BooksBean> getBooksBeen() {
        return booksBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        final BookListDetailViewHolder bookListDetailViewHolder = new BookListDetailViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRecyclerViewItemClick != null){
                    onRecyclerViewItemClick.onItemClick(view , bookListDetailViewHolder.getLayoutPosition());
                }
            }
        });

        return bookListDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookListDetailViewHolder viewHolder = (BookListDetailViewHolder) holder;
        Rankings.RankingBean.BooksBean book = booksBeen.get(position);
        //设置作者和类型
        viewHolder.tvAuthorCat.setText(book.getAuthor()+" | "+ book.getCat());
        //设置书名
        viewHolder.tvBookTitle.setText(book.getTitle());
        //设置书籍介绍
        viewHolder.tvShortIntro.setText(book.getShortIntro());
        //设置人数和留存率
        String follower = Tools.intToStr(book.getLatelyFollower());
        String s = follower + "人在追 | " + book.getRetentionRatio() + "%读者留存";
        //设置部分字体颜色
        SpannableStringBuilder style=new SpannableStringBuilder(s);
        style.setSpan(new ForegroundColorSpan(Color.RED),0,s.indexOf("人"), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.RED),s.indexOf("|")+1,s.indexOf("%")+1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        viewHolder.tvFollowerRatio.setText(style);

        //设置书籍图片
        Glide.with(BookApplication.getmContext()).load(Constant.IMG_BASE_URL+book.getCover())
                .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_RGB_565)
                .error(R.mipmap.cover_default)
                .into(viewHolder.ivBookIcon);
    }

    @Override
    public int getItemCount() {
        return booksBeen == null ? 0 : booksBeen.size();
    }

    class BookListDetailViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_book_icon)
        ImageView ivBookIcon;
        @Bind(R.id.tv_book_title)
        TextView tvBookTitle;
        @Bind(R.id.tv_author_cat)
        TextView tvAuthorCat;
        @Bind(R.id.tv_shortIntro)
        TextView tvShortIntro;
        @Bind(R.id.tv_follower_ratio)
        TextView tvFollowerRatio;
        public BookListDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

}
