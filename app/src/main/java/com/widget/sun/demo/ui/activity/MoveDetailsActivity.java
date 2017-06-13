package com.widget.sun.demo.ui.activity;

import android.os.Bundle;

import com.widget.sun.demo.R;
import com.widget.sun.demo.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by sunyunlong at 2017/5/25
 */
public class MoveDetailsActivity extends BaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.layout_activity_movedetails;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_title_back)
    void onClickBack() {
        mSwipeBackHelper.backward();
    }
}
