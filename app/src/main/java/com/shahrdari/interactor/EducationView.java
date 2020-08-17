package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import com.shahrdari.models.EducationModel;

import java.util.List;

@UiThread
public interface EducationView extends BaseView {
    void onGetEducations(List<EducationModel> educationModels);

}