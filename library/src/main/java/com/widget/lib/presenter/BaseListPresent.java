package com.widget.lib.presenter;

import com.widget.lib.listener.OnLoadDataListener;
import com.widget.lib.listener.OnRefreshLayoutListener;
import com.widget.lib.model.BaseModel;

import java.util.List;

public class BaseListPresent<T> implements OnLoadDataListener<List<T>> {

    private OnRefreshLayoutListener mOnRefreshLayoutListener;
    private BaseModel mModel;
    private boolean mOnlyOnePage = false;

    public BaseListPresent(BaseModel model) {
        this.mModel = model;
    }

    public void loadData(int pageIndex, boolean one) {
        this.mOnlyOnePage = one;
        mModel.loadData(pageIndex, this);
    }

    public void setOnRefreshLayoutListener(OnRefreshLayoutListener refreshLayoutListener) {
        this.mOnRefreshLayoutListener = refreshLayoutListener;
    }

    @Override
    public void onSuccess(List<T> data) {
        if (this.mOnRefreshLayoutListener == null) {
            return;
        }
        if (mOnlyOnePage || mModel.getPageIndex() == 1) {
            mOnRefreshLayoutListener.loadDatas(data);
        } else {
            mOnRefreshLayoutListener.loadMoreDatas(data);
        }
    }

    @Override
    public void onFailure(String error) {
        if (this.mOnRefreshLayoutListener == null) {
            return;
        }
        mOnRefreshLayoutListener.LoadFailMsg(error);
    }
}
