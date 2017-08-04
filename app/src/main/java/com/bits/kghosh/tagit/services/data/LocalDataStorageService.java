package com.bits.kghosh.tagit.services.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class LocalDataStorageService {
    private static final String PREFS_NAME = "TagItSharedPref";

    public Context context;
    public SharedPreferences settings;

    public LocalDataStorageService(Context ctx) {
        this.context = ctx;
    }

    public void saveSomething(int value) {
        this.setIntSetting("something", value);
    }

    public int getSomething() {
        return this.getIntSetting("something");
    }

    private void initializeSharedPreferences() {
        if (this.settings == null) {
            settings = this.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
    }

    private String getStringSetting(String key) {
        initializeSharedPreferences();
        return settings.getString(key, "");
    }

    private void setStringSetting(String key, String value) {
        initializeSharedPreferences();
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private int getIntSetting(String key) {
        initializeSharedPreferences();
        return settings.getInt(key, 0);
    }

    private void setIntSetting(String key, int value) {
        initializeSharedPreferences();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

}
