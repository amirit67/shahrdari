package com.shahrdari;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.multidex.MultiDexApplication;

import com.shahrdari.di.AppModule;
import com.shahrdari.di.DaggerMainComponent;
import com.shahrdari.di.MainComponent;
import com.shahrdari.di.NetModule;


public class MyApp extends MultiDexApplication {


    public static MainComponent mainComponent;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    public static Activity activity;
    private static MyApp Instance = null;

    public static MyApp getInstance() {
        return Instance;
    }

    public static MainComponent getmainComponent() {
        return mainComponent;
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static SharedPreferences.Editor getEditor() {
        return editor;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;

        mainComponent = DaggerMainComponent.builder()
                .netModule(new NetModule(BuildConfig.BASEURL))
                .appModule(new AppModule(this))
                .build();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }
}