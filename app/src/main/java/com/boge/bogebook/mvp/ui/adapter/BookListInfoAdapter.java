package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.View;

import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.BookListDetail.BookListBean.BooksBean;
import com.boge.bogebook.listener.OnBaseItemClick;
import com.boge.bogebook.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.boge.bogebook.mvp.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/11/11
 */

public class BookListInfoAdapter extends BaseRecyclerViewAdapter<BooksBean> {

    private OnBaseItemClick<BooksBean> onBaseItemClick;

    public BookListInfoAdapter(Context mContext, List<BooksBean> mList) {
        super(mContext, mList, R.layout.item_book_list_info);
    }

    public void setOnBaseItemClick(OnBaseItemClick<BooksBean> onBaseItemClick) {
        this.onBaseItemClick = onBaseItemClick;
    }

    @Override
    protected void onBindData(BaseViewHolder holder, final int position, final BooksBean item) {
        holder.setImageUrl(R.id.iv_book_icon, Constant.IMG_BASE_URL+item.getBook().getCover()
                    , R.mipmap.cover_default);
        holder.setText(R.id.tv_book_title, item.getBook().getTitle());
        holder.setText(R.id.tv_book_info, item.getBook().getAuthor()+"  |  "+
                item.getBook().getCat()+"  |  "+item.getBook().getLatelyFollower()+"人追");
        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onBaseItemClick != null){
                    onBaseItemClick.onItemClick(view, position, item);
                }
            }
        });
    }
}
