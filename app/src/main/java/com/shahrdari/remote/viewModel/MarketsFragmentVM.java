package com.shahrdari.remote.viewModel;

import androidx.lifecycle.ViewModel;

import com.shahrdari.R;
import com.shahrdari.interactor.MarketView;
import com.shahrdari.models.MarketModel;
import com.shahrdari.remote.repository.RemoteRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketsFragmentVM extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private RemoteRepository mainRemoteRepository;

    public MarketsFragmentVM() {
        mainRemoteRepository = new RemoteRepository();
    }

    public void GetMarkets(MarketView marketView) {

        mainRemoteRepository.getMarkets().enqueue(new Callback<List<MarketModel>>() {
            @Override
            public void onResponse(Call<List<MarketModel>> call, Response<List<MarketModel>> response) {
                if (response.isSuccessful()) {
                    marketView.onGetMarkets(response.body());
                } else {
                    marketView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<MarketModel>> call, Throwable t) {
                marketView.showMessage("خطا در اتصال به اینترنت");
            }
        });
    }


    public void SendComment(MarketView marketView, /*String id, */String bazarname, String number, String nazar) {

        mainRemoteRepository.sendComment(/*id,*/ bazarname, number, nazar).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    marketView.onSendComment(response.body());
                } else {
                    marketView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                marketView.showMessage("خطا در اتصال به اینترنت");
            }
        });
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
