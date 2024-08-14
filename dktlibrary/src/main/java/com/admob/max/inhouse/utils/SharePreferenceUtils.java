package com.admob.max.inhouse.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtils {

    public static final String RIZE_SHARE_PRE = "ADMOB_SHARE_PRE";
    private static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ACCESS_TOKEN = "nodeToken";
    public static final String CURRENT_AVATAR = "avatar";

    public static void savePreferences(Context activity, String key, String value) {
        SharedPreferences sp = activity.getSharedPreferences(SharePreferenceUtils.RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static void saveBoolPreferences(Context activity, String key, boolean value) {
        SharedPreferences sp = activity.getSharedPreferences(SharePreferenceUtils.RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean readBoolPreferences(Context activity, String key, boolean defaultValue) {
        SharedPreferences sp = activity.getSharedPreferences(SharePreferenceUtils.RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static String readPreferences(Context activity, String key, String defaultValue) {
        SharedPreferences sp = activity.getSharedPreferences(SharePreferenceUtils.RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void removePreference(Context activity, String string) {
        SharedPreferences sp = activity.getSharedPreferences(string, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(string);
        editor.commit();
    }

    public static void saveUsernamePassword(Context context, String userName, String password) {
        SharedPreferences sp = context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USERNAME, userName);
        editor.putString(PASSWORD, password);
        editor.apply();
    }
    public static int saveIntPreference(Context context,String key,int integer){
        SharedPreferences sp = context.getSharedPreferences(SharePreferenceUtils.RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        int current = getIntPreference(context,key);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, current + integer);
        editor.apply();
        return current+integer;
    }
    public static int getIntPreference(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(SharePreferenceUtils.RIZE_SHARE_PRE, Context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }
    public static String getUsername(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE).getString(USERNAME, null);
    }
    public static String getToken(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE).getString(ACCESS_TOKEN, null);
    }
    public static String getPassword(Context context) {
        return context.getSharedPreferences(RIZE_SHARE_PRE, Context.MODE_PRIVATE).getString(PASSWORD, null);
    }
}

