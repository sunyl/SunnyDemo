package com.widget.sun.demo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.widget.lib.utils.Res;
import com.widget.lib.view.backlayout.BGASwipeBackManager;

/**
 * created by sunyunlong at 2017/5/24
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Res.attatch(this);
        BGASwipeBackManager.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "5d35d09729", true);
    }
}
