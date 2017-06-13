package com.widget.lib.listener;

public interface OnLoadDataListener<T> {
    void onSuccess(T data);
    void onFailure(String error);
}
