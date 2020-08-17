package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import com.shahrdari.models.MarketModel;

import java.util.List;

import okhttp3.ResponseBody;

@UiThread
public interface MarketView extends BaseView {
    void onGetMarkets(List<MarketModel> productItems);

    void onSendComment(ResponseBody responseBody);

}