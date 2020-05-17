package com.shahrdari.rest;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alireza Eskandarpour Shoferi on 11/10/2017.
 */

public class HeaderInterceptor implements Interceptor {
    private final SharedPreferences preferences;

    @Inject
    public HeaderInterceptor(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = preferences.getString("", null);

        Request.Builder newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json");
        Log.i("olpldlld", "intercept: " + token);
        if (chain.request().headers().get("Authorization") == null && token != null) {
            newRequest.addHeader("Authorization", token);
        }
        return chain.proceed(newRequest.build());
    }
}
