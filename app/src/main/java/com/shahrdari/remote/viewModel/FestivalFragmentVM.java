package com.shahrdari.remote.viewModel;

import androidx.lifecycle.ViewModel;

import com.shahrdari.interactor.FestivalView;
import com.shahrdari.models.FestivalModel;
import com.shahrdari.remote.repository.RemoteRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FestivalFragmentVM extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private RemoteRepository mainRemoteRepository;

    public FestivalFragmentVM() {
        mainRemoteRepository = new RemoteRepository();
    }

    public void GetFestivals(FestivalView festivalView) {

        mainRemoteRepository.getFestivals().enqueue(new Callback<List<FestivalModel>>() {
            @Override
            public void onResponse(Call<List<FestivalModel>> call, Response<List<FestivalModel>> response) {
                if (response.isSuccessful()) {
                    festivalView.onGetFestivals(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<FestivalModel>> call, Throwable t) {
                festivalView.showMessage("خطا در اتصال به اینترنت");
            }
        });
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
