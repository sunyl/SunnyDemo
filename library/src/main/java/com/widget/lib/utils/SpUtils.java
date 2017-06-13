package com.widget.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {
    private static final String SP_REGISTER_INFO = "register_info";
    private static final String SP_SETTINGS = "sp_settings";
    private static final String CHECKED_IN_TIME = "checked_in_time";

    private static Context sContext;

    public static void attatch(Context context) {
        sContext = context;
    }

    public static void checkedIn(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("checked_in_time", System.currentTimeMillis());
        editor.apply();
    }

    public static long getCheckedInTime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(CHECKED_IN_TIME, 0);
    }

    public static void saveLoginInfo(Context context, String u, String p) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("u", u);
        editor.putString("p", p);
        editor.apply();
    }

    public static void clearLoginInfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("u", "");
        editor.putString("p", "");
        editor.apply();
    }

    public static void clearPasswrod(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("p", "");
        editor.apply();
    }

    public static String getU(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("u", "");
    }

    public static String getP(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_SETTINGS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("p", "");
    }

    public static void put(String name, String key, Object object) {
        String value = GsonUtils.toJson(object);
        put(name, key, value);
    }

    public static void put(String name, String key, String value) {
        SharedPreferences sp = sContext.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void remove(String name, String key) {
        SharedPreferences sp = sContext.getSharedPreferences(name, 0);
        sp.edit().remove(key).commit();
    }

    public static <T> T getObject(String name, String key, Class<T> cls) {
        String value = getString(name, key, "");
        return GsonUtils.fromJson(value, cls);
    }

    public static String getString(String name, String key, String defValue) {
        SharedPreferences sp = sContext.getSharedPreferences(name, 0);
        return sp.getString(key, defValue);
    }

    public static long getLastConnectTime(){
        SharedPreferences sp = sContext.getSharedPreferences("connect",0);
        return sp.getLong("last", System.currentTimeMillis());
    }
    public static void setLastConnectTime(long time){
        SharedPreferences sp = sContext.getSharedPreferences("connect",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("last",time);
        editor.apply();
    }

    public static void setLastStatus(boolean status){
        SharedPreferences sp = sContext.getSharedPreferences("connect",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("status", status);
        editor.apply();
    }

    public static boolean getLastStatus(){
        SharedPreferences sp = sContext.getSharedPreferences("connect",0);
        return sp.getBoolean("status",false);
    }
}
