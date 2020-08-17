package com.shahrdari.remote.repository;

import com.shahrdari.MyApp;
import com.shahrdari.models.BannerModel;
import com.shahrdari.models.EducationModel;
import com.shahrdari.models.FestivalModel;
import com.shahrdari.models.MarketModel;
import com.shahrdari.models.ProductItem;
import com.shahrdari.models.RateModel;
import com.shahrdari.models.WeatherModel;
import com.shahrdari.rest.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
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
    public Call<List<WeatherModel>> getWeather() {
        Call<List<WeatherModel>> getWeather =
                apiInterface.GetWeather();
        return getWeather;
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

    @Override
    public Call<List<ProductItem>> SearchProducts(String txt) {
        Call<List<ProductItem>> getProducts =
                apiInterface.SearchProducts(txt);
        return getProducts;
    }

    @Override
    public Call<List<RateModel>> getRating() {
        Call<List<RateModel>> getRating =
                apiInterface.GetRating();
        return getRating;
    }

    @Override
    public Call<List<MarketModel>> getMarkets() {
        Call<List<MarketModel>> getMarkets =
                apiInterface.GetMarkets();
        return getMarkets;
    }

    @Override
    public Call<List<FestivalModel>> getFestivals() {
        Call<List<FestivalModel>> getFestivals =
                apiInterface.GetFestivals();
        return getFestivals;
    }

    @Override
    public Call<ResponseBody> sendComment(/*String id, */String bazarname, String number, String nazar) {
        Call<ResponseBody> sendComment =
                apiInterface.SendComment(/*id, */bazarname, number, nazar);
        return sendComment;

    }

    @Override
    public Call<ResponseBody> registerClub(String name, String family, String mobile) {
        Call<ResponseBody> registerClub =
                apiInterface.RegisterClub(/*id, */name, family, mobile);
        return registerClub;
    }

    @Override
    public Call<List<EducationModel>> getEducation(String code) {
        Call<List<EducationModel>> getEducation =
                apiInterface.GetEducation(code);
        return getEducation;
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
