package com.widget.sun.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.widget.lib.view.backlayout.BGASwipeBackHelper;
import com.widget.sun.demo.ui.activity.MoveDetailsActivity;


public class OpenUtils {

    private static final long CLICK_INTERVAL_TIME = 300;
    private static long sLastClickTime = 0;

    /**
     * 检测时间间隔
     */
    public static boolean checkClickTime() {
        if (System.currentTimeMillis() - sLastClickTime < CLICK_INTERVAL_TIME) {
            return false;
        }
        sLastClickTime = System.currentTimeMillis();
        return true;
    }

    public static void openMoveDetailsActivity(BGASwipeBackHelper swipeBackHelper) {
        if (!checkClickTime()) {
            return;
        }
        swipeBackHelper.forward(MoveDetailsActivity.class);
    }
}