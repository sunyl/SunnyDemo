package com.widget.sun.demo.ui.activity;

import android.os.Bundle;

import com.widget.sun.demo.R;
import com.widget.sun.demo.ui.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_btn3)
    void onClickRecyclerView() {
        mSwipeBackHelper.forward(MoveListActivity.class);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
