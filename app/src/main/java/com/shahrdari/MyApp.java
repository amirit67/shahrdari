package com.shahrdari;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.preference.PreferenceManager;

import androidx.multidex.MultiDexApplication;

import com.shahrdari.di.AppModule;
import com.shahrdari.di.DaggerMainComponent;
import com.shahrdari.di.MainComponent;
import com.shahrdari.di.NetModule;
import com.shahrdari.remote.repository.RemoteRepository;
import com.shahrdari.utils.DataBaseHelper;

import java.io.IOException;


public class MyApp extends MultiDexApplication {


    public static SQLiteDatabase database;
    public static MainComponent mainComponent;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    public static Activity activity;
    public static RemoteRepository remoteRepository;
    private static MyApp Instance = null;

    public static RemoteRepository getRemoteRepository() {
        return remoteRepository;
    }

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

        remoteRepository = new RemoteRepository();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        try {
            DataBaseHelper db = new DataBaseHelper(getApplicationContext()) {
                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    /*String DB_PATH = Environment.getDataDirectory() + "/data/" + getBaseContext().getPackageName() + "/databases/investam.db";
                    getBaseContext().deleteDatabase(DB_PATH);*/
                }

                @Override
                public void onCreate(SQLiteDatabase db) {
                }
            };
            db.createdatabase();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = Environment.getDataDirectory() + "/data/" + getPackageName() + "/databases/investam.db";
        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    }
}