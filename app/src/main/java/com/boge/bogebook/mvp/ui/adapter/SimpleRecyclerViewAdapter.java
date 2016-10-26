package com.boge.bogebook.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by boge on 2016/10/26.
 */

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter {


    private List<String> datas;

    public SimpleRecyclerViewAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SimpleViewHolder viewHolder = (SimpleViewHolder) holder;
        viewHolder.text.setText(datas.get(position));

    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView text;
        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
