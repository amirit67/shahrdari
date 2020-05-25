package com.shahrdari.remote.repository;

import com.shahrdari.MyApp;
import com.shahrdari.models.BannerModel;
import com.shahrdari.models.ProductItem;
import com.shahrdari.rest.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RemoteRepository implements Repository {

    @Inject
    Retrofit retrofit;
    ApiInterface apiInterface;

    public RemoteRepository() {
        MyApp.getmainComponent().Inject(this);
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @Override
    public Call<List<BannerModel>> getBanners() {
        Call<List<BannerModel>> getBanner =
                apiInterface.GetBanner();
        return getBanner;
    }

    @Override
    public Call<List<ProductItem>> getProducts1(String code) {
        Call<List<ProductItem>> getProducts =
                apiInterface.GetProductsGroup1(code);
        return getProducts;
    }

    @Override
    public Call<List<ProductItem>> getProducts2(String code) {
        Call<List<ProductItem>> getProducts =
                apiInterface.GetProductsGroup2(code);
        return getProducts;
    }


    @Override
    public Call<List<ProductItem>> getProducts3(String code) {
        Call<List<ProductItem>> getProducts =
                apiInterface.GetProductsGroup3(code);
        return getProducts;
    }

    @Override
    public Call<List<ProductItem>> getProducts4(String code) {
        Call<List<ProductItem>> getProducts =
                apiInterface.GetProductsGroup4(code);
        return getProducts;
    }





  /*  @Override
    public Observable<ExpertItem> getAllExperts(Map<String, String> params) {
        Observable<ExpertItem> observable =
                apiInterface.OnlineExperts(params);
        return observable
                *//*.flatMap((Function<ExpertItem, Observable<ExpertItem>>) expertItem -> Observable.fromArray())*//*;
    }

    @Override
    public Call<ProfileItem> getExpertProfile(String id) {
        return null;
    }*/

}
