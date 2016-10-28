package com.boge.bogebook.mvp.ui.adapter.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/28
 */

public class BaseViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> mViews = new SparseArray<>();

    private View mConvertView;
    private int mLayoutId;
    protected Context mContext;

    public BaseViewHolder(Context context, int layoutId, View itemView) {
        super(itemView);
        this.mContext = context;
        this.mLayoutId = layoutId;
        mConvertView = itemView;
        mConvertView.setTag(this);
    }

    /**
     * 根据id得到控件
     * @param viewId id
     * @param <V>   控件
     * @return
     */
    public <V extends View> V getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 获取item布局
     *
     * @return
     */
    public View getItemView() {
        return mConvertView;
    }

    public BaseViewHolder setOnItemViewClickListener(View.OnClickListener listener){
        mConvertView.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置TextView的值
     * @param viewId  id
     * @param value   值
     * @return
     */
    public BaseViewHolder setText(int viewId, String value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * 设置TextView的颜色
     * @param viewId id
     * @param color  颜色RGB
     * @return
     */
    public BaseViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    /**
     * 设置TextView的颜色
     * @param viewId id
     * @param colorRes   @color/
     * @return
     */
    public BaseViewHolder setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext, colorRes));
        return this;
    }

    /**
     * 设置ImageView本地图片
     * @param viewId
     * @param imgResId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, int imgResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    /**
     * 设置控件的背景颜色
     * @param viewId
     * @param color  RGB
     * @return
     */
    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置控件的背景颜色
     * @param viewId
     * @param colorRes  资源
     * @return
     */
    public BaseViewHolder setBackgroundColorRes(int viewId, int colorRes) {
        View view = getView(viewId);
        view.setBackgroundResource(colorRes);
        return this;
    }

    /**
     * 设置ImageView的图片
     * @param viewId
     * @param drawable    图片
     * @return
     */
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setImageDrawableRes(int viewId, int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(mContext, drawableRes);
        return setImageDrawable(viewId, drawable);
    }

    /**
     * 设置ImageView的图片
     * @param viewId
     * @param imgUrl    网络路径
     * @return
     */
    public BaseViewHolder setImageUrl(int viewId, String imgUrl) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imgUrl).asBitmap().into(view);
        return this;
    }

    /**
     * 设置ImageView的图片
     * @param viewId
     * @param imgUrl    网络路径
     * @param placeHolderRes 失败时图片
     * @return
     */
    public BaseViewHolder setImageUrl(int viewId, String imgUrl, int placeHolderRes) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imgUrl).placeholder(placeHolderRes).into(view);
        return this;
    }

    /**
     * 设置控件是否可见
     * @param viewId
     * @param visibility
     * @return
     */
    public BaseViewHolder setVisibility(int viewId , int visibility){
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 设置Checkable是否选中
     * @param viewId
     * @param checked
     * @return
     */
    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }
}