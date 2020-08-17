package com.shahrdari.remote.viewModel;

import androidx.lifecycle.ViewModel;

import com.shahrdari.interactor.EducationView;
import com.shahrdari.models.EducationModel;
import com.shahrdari.remote.repository.RemoteRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationFragmentVM extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private RemoteRepository mainRemoteRepository;

    public EducationFragmentVM() {
        mainRemoteRepository = new RemoteRepository();
    }

    public void GetEducations(EducationView educationView, String code) {

        mainRemoteRepository.getEducation(code).enqueue(new Callback<List<EducationModel>>() {
            @Override
            public void onResponse(Call<List<EducationModel>> call, Response<List<EducationModel>> response) {
                if (response.isSuccessful()) {
                    educationView.onGetEducations(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<EducationModel>> call, Throwable t) {
                educationView.showMessage("خطا در اتصال به اینترنت");
            }
        });
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
