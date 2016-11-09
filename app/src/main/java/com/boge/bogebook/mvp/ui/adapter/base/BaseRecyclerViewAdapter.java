package com.boge.bogebook.mvp.ui.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boge.bogebook.listener.OnBaseItemClick;

import java.util.List;

/**
 * @author boge
 * @version 1.0
 * @date 2016/10/14
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected List<T> mList;
    protected int[] layoutIds;
    protected LayoutInflater mLInflater;

    private SparseArray<View> mConvertViews = new SparseArray<>();

    public BaseRecyclerViewAdapter(Context mContext, List<T> mList, int... layoutIds) {
        this.mContext = mContext;
        this.mList = mList;
        this.layoutIds = layoutIds;
        this.mLInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < 0 || viewType > layoutIds.length) {
            throw new ArrayIndexOutOfBoundsException("layoutIndex");
        }
        if (layoutIds.length == 0) {
            throw new IllegalArgumentException("not layoutId");
        }
        int layoutId = layoutIds[viewType];
        View view = mConvertViews.get(layoutId);
        if (view == null) {
            view = mLInflater.inflate(layoutId, parent, false);
        }
        BaseViewHolder viewHolder = (BaseViewHolder) view.getTag();
        if (viewHolder == null || viewHolder.getLayoutId() != layoutId) {
            viewHolder = new BaseViewHolder(mContext , layoutId , view);
        }
        final BaseViewHolder finalViewHolder = viewHolder;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onBaseItemClick != null){
                    onBaseItemClick.onItemClick(view , finalViewHolder.getLayoutPosition() , mList.get(finalViewHolder.getLayoutPosition()));
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final T item = mList.get(position);
        onBindData(holder, position, item);
    }

    protected abstract void onBindData(BaseViewHolder holder, int position, T item);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIndex(position, mList.get(position));
    }

    /**
     * 指定item布局样式在layoutIds的索引。默认为第一个
     *
     * @param position
     * @param item
     * @return
     */
    public int getLayoutIndex(int position, T item) {
        return 0;
    }

    public void setmList(List<T> list){
        mList = list;
        notifyDataSetChanged();
    }

    public boolean addAll(List<T> list) {
        boolean result = mList.addAll(list);
        notifyDataSetChanged();
        return result;
    }

    public boolean addAll(int position, List list) {
        boolean result = mList.addAll(position, list);
        notifyDataSetChanged();
        return result;
    }

    public void add(T data) {
        mList.add(data);
        notifyDataSetChanged();
    }

    public void add(int position, T data) {
        mList.add(position, data);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public boolean contains(T data) {
        return mList.contains(data);
    }

    public T getData(int index) {
        return mList.get(index);
    }

    public List<T> getmList(){
        return mList;
    }

    public void modify(T oldData, T newData) {
        modify(mList.indexOf(oldData), newData);
    }

    public void modify(int index, T newData) {
        mList.set(index, newData);
        notifyDataSetChanged();
    }

    public boolean remove(T data) {
        boolean result = mList.remove(data);
        notifyDataSetChanged();
        return result;
    }

    public void remove(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }

    private OnBaseItemClick onBaseItemClick;

    public void OnBaseItemClick(OnBaseItemClick listener) {
        this.onBaseItemClick = listener;
    }
}
