package com.widget.sun.demo.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.widget.lib.utils.Res;
import com.widget.sun.demo.R;
import com.widget.sun.demo.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_channel)
    TextView mTvChannel;

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
        mTvChannel.setText(Res.getMetaValue("UMENG_CHANNEL"));
    }

    @OnClick(R.id.btn_btn3)
    void onClickRecyclerView() {
        mSwipeBackHelper.forward(MoveListActivity.class);
    }

    @OnClick(R.id.btn_btn1)
    void onClickMaiDian() {
        MobclickAgent.onEvent(this, "01");
    }

    @OnClick(R.id.btn_btn2)
    void onClickMaiDian2() {
        MobclickAgent.onEvent(this, "02");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
