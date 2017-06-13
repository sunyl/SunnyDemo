package com.widget.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * created by sunyunlong at 2017/5/25
 * 只需要重写 updateItem
 */
public class EasyAdapter<T> extends RecyclerView.Adapter {

    protected Context mContext;
    protected ArrayList<T> mData;
    protected int mLayouId;

    public OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener extends View.OnLongClickListener {
        boolean onItemLongClick(int position);
    }

    public EasyAdapter(Context context, ArrayList<T> data, int layouid) {
        mContext = context;
        mData = data;
        mLayouId = layouid;
    }

    public void setResId(int layoutid) {
        mLayouId = layoutid;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(mLayouId, parent, false);
        onViewCreated(root);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(root) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        updateItem(holder, mData.get(position), position);
        setClickListener(holder, position);
    }

    private void setClickListener(final RecyclerView.ViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClick(position);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        updateItem(holder, mData.get(position), position, payloads);
        setClickListener(holder, position);
    }

    /**
     * 更新视图
     */
    public void updateItem(RecyclerView.ViewHolder holder, T entity, int position) {

    }

    /**
     * 更新视图
     */
    public void updateItem(RecyclerView.ViewHolder holder, T entity, int position, List payloads) {
        if (payloads.isEmpty()) {
            updateItem(holder, entity, position);
        }
    }

    /**
     * 视图 被创建出来
     */
    public void onViewCreated(View root) {

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }
}
