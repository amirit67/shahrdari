package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import com.shahrdari.models.RateModel;

import java.util.List;

@UiThread
public interface RateView extends BaseView {
    void onGetRates(List<RateModel> rateModels);

}