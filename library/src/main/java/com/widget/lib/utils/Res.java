package com.widget.lib.utils;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.File;

public class Res {

    private static int sScreenX;
    private static int sScreenY;

    private static SparseIntArray sCachedColor = new SparseIntArray();
    private static SparseArray<String> sCachedString = new SparseArray<>();

    private static Context sContext;

    public static void attatch(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    public static File getDir(String name, int mode) {
        return sContext.getDir(name, mode);
    }

    public static DisplayMetrics getDisplayMetrics() {
        return sContext.getResources().getDisplayMetrics();
    }

    public static Drawable getDrawable(int resId) {
        return sContext.getResources().getDrawable(resId);
    }

    public static <T extends View> T inflate(int resource, ViewGroup root) {
        return (T) LayoutInflater.from(sContext).inflate(resource, root);
    }

    public static <T extends View> T inflate(int resource, ViewGroup root, boolean attachToRoot) {
        return (T) LayoutInflater.from(sContext).inflate(resource, root, attachToRoot);
    }

    public static String getString(int resId) {
        String str = sCachedString.get(resId);
        if (str == null) {
            str = sContext.getResources().getString(resId);
            sCachedString.put(resId, str);
        }
        return str;
    }

    public static String getString(int resId, Object... formatArgs) {
        return sContext.getString(resId, formatArgs);
    }

    public static int getColor(int resId) {
        int color = sCachedColor.get(resId);
        if (color == 0) {
            color = sContext.getResources().getColor(resId);
            sCachedColor.put(resId, color);
        }
        return color;
    }

    public static int getResInteger(int resId) {
        return sContext.getResources().getInteger(resId);
    }

    public static ColorStateList getColorStateList(int resId) {
        return sContext.getResources().getColorStateList(resId);
    }

    public static String[] getStringArray(int resId) {
        return sContext.getResources().getStringArray(resId);
    }

    public static int[] getIntArray(int resId) {
        return sContext.getResources().getIntArray(resId);
    }

    public static Resources getResources() {
        return sContext.getResources();
    }

    public static AssetManager getAssets() {
        return sContext.getAssets();
    }

    public static PackageManager getPackageManager() {
        return sContext.getPackageManager();
    }


    public static ApplicationInfo getApplicationInfo() {
        return sContext.getApplicationInfo();
    }

    public static String getPackageName() {
        return sContext.getPackageName();
    }

    public static Drawable getAppDrawable(String pkgName) {
        try {
            ApplicationInfo appInfo = Res.getPackageManager().getApplicationInfo(pkgName, 0);
            Drawable drawable = appInfo.loadIcon(Res.getPackageManager());
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getCacheDir() {
        return sContext.getCacheDir();
    }

    public static File getFilesDir() {
        return sContext.getFilesDir();
    }

    public static File getExternalCacheDir() {
        return sContext.getExternalCacheDir();
    }

    public static File getExternalFilesDir(String type) {
        return sContext.getExternalFilesDir(type);
    }

    public static ContentResolver getContentResolver() {
        return sContext.getContentResolver();
    }

    private static void getDisplay() {
        final DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) sContext.getSystemService(Service.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        sScreenX = dm.widthPixels;
        sScreenY = dm.heightPixels;
    }

    public static int getScreenX() {
        if (sScreenX == 0) {
            getDisplay();
        }
        return sScreenX;
    }

    public static int getScreenY() {
        if (sScreenY == 0) {
            getDisplay();
        }
        return sScreenY;
    }

    public static Object getSystemService(String name) {
        return sContext.getSystemService(name);
    }

    public static int dp2px(int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, sContext.getResources().getDisplayMetrics()) + 0.5);
    }

    public static float dp2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, sContext.getResources().getDisplayMetrics()) + 0.5);
    }

    public static int px2dp(float pxValue) {
        final float scale = sContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(float sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, sContext.getResources().getDisplayMetrics()) + 0.5);
    }

    public static float getDensity() {
        return sContext.getResources().getDisplayMetrics().density;
    }

    public static float getDimen(int id) {
        return sContext.getResources().getDimension(id);
    }

    public static int getDimenPixelOffset(int id) {
        return sContext.getResources().getDimensionPixelOffset(id);
    }

    public static int getDimenPixelSize(int id) {
        return sContext.getResources().getDimensionPixelSize(id);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = sContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = sContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getTargetSdkVersion() {
        int targetSdkVersion = 0;
        try {
            final PackageInfo info = sContext.getPackageManager().getPackageInfo(sContext.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return targetSdkVersion;
    }

    /**
     * 获取应用当前版本
     */
    public static String getCurVersionName() {
        try {
            PackageInfo pkgInfo = sContext.getPackageManager().getPackageInfo(sContext.getPackageName(), 0);
            return pkgInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }

    public static int getCurVersionCode() {
        try {
            PackageInfo pkgInfo = sContext.getPackageManager().getPackageInfo(sContext.getPackageName(), 0);
            return pkgInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取AndroidMainfest里meta的值
     */
    public static String getMetaValue(String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = sContext.getPackageManager().getApplicationInfo(sContext.getPackageName(), PackageManager.GET_META_DATA);
            if (ai != null) {
                metaData = ai.metaData;
            }
            if (metaData != null) {
                apiKey = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return apiKey;
    }

    public static int getIntMetaValue(String metaKey) {
        if (metaKey == null) {
            return 0;
        }
        Bundle metaData = null;
        int apiKey = 0;
        try {
            ApplicationInfo ai = sContext.getPackageManager().getApplicationInfo(sContext.getPackageName(), PackageManager.GET_META_DATA);
            if (ai != null) {
                metaData = ai.metaData;
            }
            if (metaData != null) {
                apiKey = metaData.getInt(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return apiKey;
    }

    /**
     * 类说明： 由"字符串标识"获取资源"数字id"
     * 如 "R.string.textview", "R.color.white", "R.layout.main"
     */
    public static int getResourceId(String text) {
        String[] arr = text.split("\\.");
        if (arr.length < 3) {
            return 0;
        }
        if (!arr[arr.length - 3].equals("R")) {
            return 0;
        }
        return sContext.getResources().getIdentifier(arr[arr.length - 1], arr[arr.length - 2], getPackageName());
    }

    public static boolean isPkgInstall(String pkgName) {
        try {
            sContext.getPackageManager().getPackageInfo(pkgName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /** 获取设备id */
    public static String getDevUid() {
        if(Build.VERSION.SDK_INT < 23) {
            TelephonyManager tm = (TelephonyManager)sContext.getSystemService(Context.TELEPHONY_SERVICE);
            String tmDevice = tm.getDeviceId();
            String tmSerial = tm.getSimSerialNumber();
            String androidid = Settings.Secure.getString(sContext.getContentResolver(), Settings.Secure.ANDROID_ID);
            String end = tmDevice + tmSerial + androidid;
            String devid = Md5.md5(end);
            return devid;
        } else {
            return newDevId();
        }
    }

    private static String newDevId() {
        String mac = getMacAddress();
        String androidid = Settings.Secure.getString(sContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        String end = mac + androidid;
        return Md5.md5(end);
    }

    public static String getMacAddress() {
        WifiManager wm = (WifiManager)sContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wm.getConnectionInfo();
        if(wifiInfo != null) {
            return wifiInfo.getMacAddress();
        }
        return "";
    }
}