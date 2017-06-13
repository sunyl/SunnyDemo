package com.widget.lib.listener;

import java.util.List;

public interface OnRefreshLayoutListener<T> {
    void loadDatas(List<T> newsList);
    void loadMoreDatas(List<T> addList);
    void LoadFailMsg(String error);
}
