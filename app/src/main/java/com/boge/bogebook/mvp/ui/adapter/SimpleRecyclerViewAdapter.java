package com.boge.bogebook.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.listener.OnRecyclerViewItemClick;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by boge on 2016/10/26.
 */

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter {


    private List<String> datas;

    private Map<String,Boolean> chooseMap;

    private int index = 0;

    public SimpleRecyclerViewAdapter(List<String> datas) {
        this(datas, 0);
    }

    public SimpleRecyclerViewAdapter(List<String> datas, int position) {
        this.datas = datas;
        setChooseMap(position);
    }

    public SimpleRecyclerViewAdapter() {}

    public void setDatas(List<String> datas) {
        this.datas = datas;
        setChooseMap(0);
        notifyDataSetChanged();
    }

    public void setDatas(List<String> datas, int position) {
        this.datas = datas;
        setChooseMap(position);
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return datas;
    }

    public void setChooseMap(int position) {
        index = position;
        chooseMap = new HashMap<String, Boolean>();
        if(datas != null){
            for (int i = 0 ; i < datas.size() ; i++){
                if(i == position){
                    chooseMap.put(datas.get(i) , true);
                }else{
                    chooseMap.put(datas.get(i) , false);
                }
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        final SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index != viewHolder.getLayoutPosition()){
                    chooseMap.put(datas.get(viewHolder.getLayoutPosition()) , true);
                    chooseMap.put(datas.get(index) , false);
                    index = viewHolder.getLayoutPosition();
                    notifyDataSetChanged();
                }
                if(onRecyclerViewItemClick != null){
                    onRecyclerViewItemClick.onItemClick(view , viewHolder.getLayoutPosition());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SimpleViewHolder viewHolder = (SimpleViewHolder) holder;
        viewHolder.text.setText(datas.get(position));
        if(chooseMap.get(datas.get(position))){
            viewHolder.text.setTextColor(BookApplication.getmContext().getResources().getColor(R.color.dark_red));
        }else{
            viewHolder.text.setTextColor(BookApplication.getmContext().getResources().getColor(R.color.light_black));
        }
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

    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }
}
