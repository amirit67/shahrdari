package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import com.shahrdari.models.BannerModel;

import java.util.List;

@UiThread
public interface MainView extends BaseView {
    void onGetBanner(List<BannerModel> bannerModel);

}