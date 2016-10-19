package com.boge.bogebook.mvp.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.boge.bogebook.BookApplication;
import com.boge.bogebook.R;
import com.boge.bogebook.common.Constant;
import com.boge.bogebook.entity.RankingList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by boge on 2016/10/19.
 */

public class RankingTopAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private LayoutInflater inflater;

    private List<RankingList.MaleBean> groupArray;
    private List<List<RankingList.MaleBean>> childArray;

    public RankingTopAdapter(Context context, List<RankingList.MaleBean> groupArray, List<List<RankingList.MaleBean>> childArray) {
        this.childArray = childArray;
        this.groupArray = groupArray;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        Log.i("test" , "size:"+groupArray.size());
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final View group = inflater.inflate(R.layout.item_ranking_group, null);
        ImageView ivCover = (ImageView) group.findViewById(R.id.ivRankCover);
        if (!TextUtils.isEmpty(groupArray.get(groupPosition).getCover())) {
            Glide.with(BookApplication.getmContext())
                    .load(Constant.IMG_BASE_URL + groupArray.get(groupPosition).getCover())
                    .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .into(ivCover);
        }else{
            ivCover.setImageResource(R.mipmap.ic_rank_collapse);
        }
        TextView tvName = (TextView) group.findViewById(R.id.tvRankGroupName);
        tvName.setText(groupArray.get(groupPosition).getTitle());
        ImageView ivArrow = (ImageView) group.findViewById(R.id.ivRankArrow);
        if (childArray.get(groupPosition).size() > 0) {
            if (isExpanded) {
                ivArrow.setImageResource(R.mipmap.rank_arrow_up);
            } else {
                ivArrow.setImageResource(R.mipmap.rank_arrow_down);
            }
        } else {
            ivArrow.setVisibility(View.GONE);
        }
        Log.i("test" , "position:"+groupPosition+","+groupArray.get(groupPosition).toString());
        return group;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final View child = inflater.inflate(R.layout.item_ranking_child, null);

        TextView tvName = (TextView) child.findViewById(R.id.tvRankChildName);
        tvName.setText(childArray.get(groupPosition).get(childPosition).getTitle());
        return child;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
