package com.widget.lib.model;


import com.widget.lib.listener.OnLoadDataListener;

public class BaseModel {

    protected int pageIndex = 1;

    public void loadData(int pageIndex, OnLoadDataListener listener) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

}
