package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import com.shahrdari.models.FestivalModel;

import java.util.List;

@UiThread
public interface FestivalView extends BaseView {
    void onGetFestivals(List<FestivalModel> festivalModels);

}