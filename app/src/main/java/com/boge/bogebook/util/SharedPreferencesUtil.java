package com.boge.bogebook.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created boge lfh on 2016/11/02.
 */
public class SharedPreferencesUtil {

    private static SharedPreferencesUtil prefsUtil;
    public Context context;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public synchronized static SharedPreferencesUtil getInstance() {
        return prefsUtil;
    }

    public static void init(Context context, String prefsname, int mode) {
        prefsUtil = new SharedPreferencesUtil();
        prefsUtil.context = context;
        prefsUtil.prefs = prefsUtil.context.getSharedPreferences(prefsname, mode);
        prefsUtil.editor = prefsUtil.prefs.edit();
    }

    private SharedPreferencesUtil() {}

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }

    public boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }


    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public String getString(String key) {
        return this.prefs.getString(key, null);
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }

    public float getFloat(String key, float defaultVal) {
        return this.prefs.getFloat(key, defaultVal);
    }

    public float getFloat(String key) {
        return this.prefs.getFloat(key, 0f);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0l);
    }


    public boolean exists(String key) {
        return prefs.contains(key);
    }

    public SharedPreferencesUtil putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil remove(String key) {
        editor.remove(key);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil removeAll() {
        editor.clear();
        editor.commit();
        return this;
    }
}
