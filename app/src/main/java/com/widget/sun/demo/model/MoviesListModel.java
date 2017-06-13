package com.widget.sun.demo.model;

import com.widget.lib.listener.OnLoadDataListener;
import com.widget.lib.model.BaseModel;
import com.widget.sun.demo.entity.MoveSubject;
import com.widget.sun.demo.http.HttpMethods;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


public class MoviesListModel extends BaseModel {

    private static final int PAGE_SIZE = 10;

    @Override
    public void loadData(final int pageIndex, final OnLoadDataListener listener) {
        super.loadData(pageIndex, listener);
        int start = 10 * (pageIndex - 1);

        HttpMethods.getInstance().getTopMovie(new Subscriber<List<MoveSubject>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    int code = httpException.code();
                    if (code == 500 || code == 404) {
                        listener.onFailure("服务器出错!");
                    }
                } else if (e instanceof ConnectException) {
                    listener.onFailure("网络断开,请打开网络!");
                } else if (e instanceof SocketTimeoutException) {
                    listener.onFailure("网络连接超时!");
                } else {
                    listener.onFailure("发生未知错误:" + e.getMessage());
                }
            }

            @Override
            public void onNext(List<MoveSubject> subjects) {
                listener.onSuccess(subjects);
            }
        }, start, PAGE_SIZE);
    }
}
