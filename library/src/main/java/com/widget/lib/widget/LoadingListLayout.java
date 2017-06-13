package com.widget.lib.widget;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.widget.sun.lib.R;
import com.widget.lib.listener.OnRefreshLayoutListener;
import com.widget.lib.presenter.BaseListPresent;
import com.widget.lib.view.CustomRefreshLayout;
import com.widget.lib.view.DividerItemDecoration;
import com.widget.lib.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by sunyunlong at 2017/5/22
 */
public class LoadingListLayout<T> implements OnRefreshLayoutListener<T> {

    private View mRoot;
    private boolean mOnlyOnePage = false;
    private CustomRefreshLayout mRefreshLayout;
    private XRecyclerView mXRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private BaseListPresent mBaseListPresent;
    private int mPageIndex = 1;
    private ArrayList<T> mData;

    public void init() {
        mRefreshLayout = (CustomRefreshLayout) mRoot.findViewById(R.id.refresh);
        mXRecyclerView = (XRecyclerView) mRoot.findViewById(R.id.recyclerView);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(mRoot.getContext()));
        mXRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mXRecyclerView.addItemDecoration(new DividerItemDecoration(mRoot.getContext(), DividerItemDecoration.VERTICAL_LIST));
        mXRecyclerView.setHasFixedSize(true);
        mXRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.showLoading();
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mBaseListPresent.loadData(mPageIndex, mOnlyOnePage);
            }

            @Override
            public void onLoadMore() {
                mPageIndex++;
                mBaseListPresent.loadData(mPageIndex, mOnlyOnePage);
            }
        });
        mBaseListPresent.loadData(mPageIndex, mOnlyOnePage);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    public void setPresent(BaseListPresent<T> present) {
        this.mBaseListPresent = present;
        this.mBaseListPresent.setOnRefreshLayoutListener(this);
    }

    public LoadingListLayout(View root) {
        mRoot = root;
    }

    public void setOnlyOnePage(boolean one) {
        mOnlyOnePage = one;
    }

    public RecyclerView getRecyclerView() {
        return mXRecyclerView;
    }

    public void setData(ArrayList<T> data) {
        mData = data;
    }

    @Override
    public void loadDatas(List<T> newsList) {
        mData.clear();
        mData.addAll(newsList);
        mAdapter.notifyDataSetChanged();
        mXRecyclerView.refreshComplete();
        if (newsList.size() == 0) {
            mRefreshLayout.showEmpty();
        } else {
            mRefreshLayout.showContent();
        }
    }

    @Override
    public void loadMoreDatas(List<T> addList) {
        if (addList.size() == 0) {
            mXRecyclerView.setNoMore(true);
        } else {
            mData.addAll(addList);
            mAdapter.notifyDataSetChanged();
            mXRecyclerView.loadMoreComplete();
            mRefreshLayout.showContent();
        }
    }

    @Override
    public void LoadFailMsg(String error) {
        mXRecyclerView.loadMoreComplete();
        mXRecyclerView.refreshComplete();

        mRefreshLayout.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPageIndex = 1;
                mRefreshLayout.showLoading();
                mBaseListPresent.loadData(mPageIndex, false);
            }
        });
    }
}
