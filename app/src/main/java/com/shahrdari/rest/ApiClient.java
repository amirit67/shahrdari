package com.shahrdari.rest;

import android.content.SharedPreferences;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shahrdari.BuildConfig;
import com.shahrdari.Constants;
import com.shahrdari.MyApp;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static final String BASE_URL = BuildConfig.BASEURL;

    private static Retrofit retrofit = null;
    private static SharedPreferences preferences = null;

    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static RxJava2CallAdapterFactory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
    private static OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
            .addInterceptor(
                    new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            String token = MyApp.getInstance().getPreferences().getString(Constants.Token, "");
                            Request request = chain.request().newBuilder().addHeader("Authorization", Objects.requireNonNull(token)).build();
                            return chain.proceed(request);
                        }
                    }).build();


    @Inject
    ApiClient(SharedPreferences preferences) {
        ApiClient.preferences = preferences;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .addConverterFactory(scalarsConverterFactory)
                    .client(defaultHttpClient)
                    .build();
        }
        return retrofit;
    }

}


