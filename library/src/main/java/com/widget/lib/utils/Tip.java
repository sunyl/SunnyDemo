package com.widget.lib.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.widget.sun.lib.R;

public class Tip {

    public static void show(Context context, int resId, int duration) {
        show(context, Res.getString(resId), duration);
    }

    public static void show(Context context, String text, int duration) {
        Runnable runnable= new RunnableInternal(context, text, duration);
        if(RunUtils.isMainThread()) {
            runnable.run();
        } else {
            RunUtils.runOnUIThread(runnable);
        }
    }

    /** 辅助 */
    private static class RunnableInternal implements Runnable {

        private final Context context;
        private final String text;
        private final int duration;

        public RunnableInternal(Context context, String text, int duration) {
            this.context = context;
            this.text = text;
            this.duration = duration;
        }

        @Override
        public void run() {
            if(context == null) {
                return ;
            }
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            View toastView = toast.getView();

            TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
            toastMessage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            toastMessage.setTextColor(Color.WHITE);
            toastMessage.setGravity(Gravity.CENTER);
            toastMessage.setPadding(Res.dp2px(14), Res.dp2px(10), Res.dp2px(14), Res.dp2px(10));
            toastMessage.setBackgroundDrawable(null);
            toastView.setBackgroundResource(R.drawable.bg_loading_dialog);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(duration);
            toast.show();
        }
    }
}