package com.shahrdari.remote.viewModel;

import androidx.lifecycle.ViewModel;

import com.shahrdari.R;
import com.shahrdari.interactor.ClubView;
import com.shahrdari.remote.repository.RemoteRepository;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubFragmentVM extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private RemoteRepository mainRemoteRepository;

    public ClubFragmentVM() {
        mainRemoteRepository = new RemoteRepository();
    }

    public void GetFestivals(ClubView clubView, String name, String family, String mobile) {

        mainRemoteRepository.registerClub(name, family, mobile).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    clubView.onRegisterClub(response.body());
                } else {
                    clubView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                clubView.showMessage("خطا در اتصال به اینترنت");
            }
        });
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
