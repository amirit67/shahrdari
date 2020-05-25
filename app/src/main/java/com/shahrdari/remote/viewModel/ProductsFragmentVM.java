package com.shahrdari.remote.viewModel;

import androidx.lifecycle.ViewModel;

import com.shahrdari.interactor.ProductsView;
import com.shahrdari.models.ProductItem;
import com.shahrdari.remote.repository.RemoteRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragmentVM extends ViewModel {

    private CompositeDisposable mCompositeDisposable;
    private RemoteRepository mainRemoteRepository;

    public ProductsFragmentVM() {
        mainRemoteRepository = new RemoteRepository();
    }

    public void GetProducts1(ProductsView productsView, String code) {

        mainRemoteRepository.getProducts1(code).enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    productsView.onGetProducts(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
    }

    public void GetProducts2(ProductsView productsView, String code) {

        mainRemoteRepository.getProducts2(code).enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    productsView.onGetProducts(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
    }

    public void GetProducts3(ProductsView productsView, String code) {

        mainRemoteRepository.getProducts3(code).enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    productsView.onGetProducts(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
    }

    public void GetProducts4(ProductsView productsView, String code) {

        mainRemoteRepository.getProducts4(code).enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    productsView.onGetProducts(response.body());
                } else {
                    //mainView.showMessage(R.string.general_error);
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
