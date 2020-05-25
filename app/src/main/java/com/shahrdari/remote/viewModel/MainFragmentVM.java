package com.shahrdari.remote.viewModel;

import androidx.lifecycle.ViewModel;

import com.shahrdari.interactor.MainView;
import com.shahrdari.models.BannerModel;
import com.shahrdari.remote.repository.RemoteRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentVM extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private RemoteRepository mainRemoteRepository;

    public MainFragmentVM() {
        mainRemoteRepository = new RemoteRepository();
    }

    public void GetBanners(MainView mainView) {

        mainRemoteRepository.getBanners().enqueue(new Callback<List<BannerModel>>() {
            @Override
            public void onResponse(Call<List<BannerModel>> call, Response<List<BannerModel>> response) {
                if (response.isSuccessful()) {

                    mainView.onGetBanner(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<BannerModel>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
