package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import okhttp3.ResponseBody;

@UiThread
public interface ClubView extends BaseView {
    void onRegisterClub(ResponseBody responseBody);

}