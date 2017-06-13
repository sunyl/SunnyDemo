package com.widget.lib.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Set;

public final class Logger {

    private static boolean debug = true;
    private static final String TAG = "Logger";
    private static String tag;
    private static final int max = 200;

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String getTag() {
        if(tag == null) {
            return TAG;
        }
        return tag;
    }

    public static void setDebug(boolean value) {
        debug = value;
    }

    public static void tag(String tag) {
        Logger.tag = tag;
    }

    public static void i(String format, Object... args) {
        log(Log.INFO, format, args);
    }

    public static void d(String format, Object... args) {
        log(Log.DEBUG, format, args);
    }

    public static void e(String format, Object... args) {
        log(Log.ERROR, format, args);
    }

    public static void v(String format, Object... args) {
        log(Log.VERBOSE, format, args);
    }

    public static void json(Object object) {
        if(!debug) {
            return ;
        }
        if(object == null) {
            return ;
        }
        Logger.i(object.getClass().getName() + "\n" + gson.toJson(object));
    }

    private static class IntentInfo {
        String action;
        Bundle bundle;
        String data;
        Set<String> categories;
        ComponentName componentName;
        int flags;
    }

    public static void intent(Intent intent) {
        if(!debug) {
            return ;
        }
        IntentInfo intentInfo = new IntentInfo();
        intentInfo.action = intent.getAction();
        intentInfo.bundle = intent.getExtras();
        intentInfo.data = intent.getDataString();
        intentInfo.categories = intent.getCategories();
        intentInfo.componentName = intent.getComponent();
        intentInfo.flags = intent.getFlags();
        json(intentInfo);
    }

    protected static void log(int priority, String format, Object... args) {
        if(!debug) {
            return ;
        }
        String message = format;
        if(args.length > 0) {
            message = String.format(format, args);
        }
        log(priority, getTag(), message, null);
    }

    protected static void log(int priority, String tag, String message, Throwable t) {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return ;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(Logger.class.getName())) {
                continue;
            }

            String fullClassName = st.getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = st.getMethodName();
            String lineNumber = String.valueOf(st.getLineNumber());

            boolean isMainThread = (Looper.getMainLooper() == Looper.myLooper());
            String msg;
            if(!isMainThread) {
                long id = Thread.currentThread().getId();
                msg = String.format("at %s.%s(%s:%s)(%d)-%s", className, methodName, st.getFileName(), lineNumber, id, message);
            } else {
                msg = String.format("at %s.%s(%s:%s)-%s", className, methodName, st.getFileName(), lineNumber, message);
            }
            if(msg.length() > max) {
                int length = (msg.length()+max-1)/max;
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < length; ++i) {
                    if(i < length - 1) {
                        sb.append(msg.substring(i*max, (i+1)*max));
                        sb.append("\n");
                    } else {
                        sb.append(msg.substring(i*max, msg.length()));
                    }
                }
                msg = sb.toString();
            }
            Log.println(priority, tag, msg);
            return ;
        }
    }
}