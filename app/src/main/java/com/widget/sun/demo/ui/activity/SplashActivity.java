package com.widget.sun.demo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.widget.sun.demo.R;
import com.widget.sun.demo.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_splash_version) TextView mTvVersion;

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        mTvVersion.setText("v" + getCurrentVersionName());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeBackHelper.forwardAndFinish(MainActivity.class);
            }
        }, 2000);
    }

    /**
     * 获取当前版本名称
     *
     * @return
     */
    public String getCurrentVersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            return "";
        }
    }
}
