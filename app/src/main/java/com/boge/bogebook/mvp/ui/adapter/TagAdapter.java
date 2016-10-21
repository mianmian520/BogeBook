package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boge.bogebook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */
public class TagAdapter<T> extends BaseAdapter{

    private final Context mContext;
    private final List<T> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        switch (position%8){
            case 0:
                textView.setBackgroundResource(R.color.tag0);
                break;
            case 1:
                textView.setBackgroundResource(R.color.tag1);
                break;
            case 2:
                textView.setBackgroundResource(R.color.tag2);
                break;
            case 3:
                textView.setBackgroundResource(R.color.tag3);
                break;
            case 4:
                textView.setBackgroundResource(R.color.tag4);
                break;
            case 5:
                textView.setBackgroundResource(R.color.tag5);
                break;
            case 6:
                textView.setBackgroundResource(R.color.tag6);
                break;
            case 7:
                textView.setBackgroundResource(R.color.tag7);
                break;
        }
        T t = mDataList.get(position);
        if (t instanceof String) {
            textView.setText((String) t);
        }
        return view;
    }

    public void onlyAddAll(List<T> datas) {
        mDataList.clear();
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

}