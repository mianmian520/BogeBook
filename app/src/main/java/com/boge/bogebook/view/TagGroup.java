package com.boge.bogebook.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/21
 */

public class TagGroup extends ViewGroup {


    /**
     * 子View的宽度，如果为0 则为warp_content
     */
    private int mWidth;

    AdapterDataSetObserver mDataSetObserver;

    ListAdapter mAdapter;
    /**
     * 存储选中的tag
     */
    private SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();

    public TagGroup(Context context) {
        super(context);
    }

    public TagGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取Padding
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = View.MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = View.MeasureSpec.getMode(heightMeasureSpec);

        //FlowLayout最终的宽度和高度值
        int resultWidth = 0;
        int resultHeight = 0;

        //测量时每一行的宽度
        int lineWidth = 0;
        //测量时每一行的高度，加起来就是FlowLayout的高度
        int lineHeight = 0;

        //遍历子控件，记录每个子view的位置
        for (int i = 0 ; i < getChildCount() ; i++){
            View childView = getChildAt(i);

            //测量每一个子view的宽和高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin;
            int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin;

            //如果当前一行的宽度加上要加入的子view的宽度大于父容器给的宽度，就换行
            if ((lineWidth + realChildWidth) > sizeWidth) {
                //换行
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += realChildHeight;
                //换行了，lineWidth和lineHeight重新算
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;
            } else {
                //不换行，直接相加
                lineWidth += realChildWidth;
                //每一行的高度取二者最大值
                lineHeight = Math.max(lineHeight, realChildHeight);
            }

            //遍历到最后一个的时候，肯定走的是不换行
            if (i == getChildCount() - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }

            setMeasuredDimension(modeWidth == View.MeasureSpec.EXACTLY ? sizeWidth : resultWidth,
                    modeHeight == View.MeasureSpec.EXACTLY ? sizeHeight : resultHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int flowWidth = getWidth();

        int childLeft = 0;
        int childTop = 0;

        //遍历子控件，记录每个子view的位置
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);

            //跳过View.GONE的子View
            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();

            if (childLeft + mlp.leftMargin + childWidth + mlp.rightMargin > flowWidth) {
                //换行处理
                childTop += (mlp.topMargin + childHeight + mlp.bottomMargin);
                childLeft = 0;
            }
            //布局
            int left = childLeft + mlp.leftMargin;
            int top = childTop + mlp.topMargin;
            int right = childLeft + mlp.leftMargin + childWidth;
            int bottom = childTop + mlp.topMargin + childHeight;
            childView.layout(left, top, right, bottom);

            childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            reloadData();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    private void reloadData() {
        removeAllViews();
        ViewGroup.MarginLayoutParams mMarginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mWidth != 0) {
            mMarginLayoutParams.width = mWidth;
        }
        //添加adapter中的view
        for (int i = 0; i < mAdapter.getCount(); i++) {
            final View childView = mAdapter.getView(i, null, this);
            addView(childView, mMarginLayoutParams);
        }
    }

    /**
     * 像ListView、GridView一样使用FlowLayout
     *
     * @param adapter
     */
    public void setAdapter(ListAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        //清除现有的数据
        removeAllViews();
        mAdapter = adapter;

        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }
}
