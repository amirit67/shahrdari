package com.shahrdari.remote.repository;


import com.shahrdari.models.BannerModel;
import com.shahrdari.models.EducationModel;
import com.shahrdari.models.FestivalModel;
import com.shahrdari.models.MarketModel;
import com.shahrdari.models.ProductItem;
import com.shahrdari.models.RateModel;
import com.shahrdari.models.WeatherModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

//import io.reactivex.Observable;

public interface Repository {

    /*
     * Get list of popular Experts
     */
    /*  Observable<ExpertItem> getAllExperts(Map<String, String> params);*/

    Call<List<BannerModel>> getBanners();

    Call<List<WeatherModel>> getWeather();

    Call<List<ProductItem>> getProducts1(String code);

    Call<List<ProductItem>> getProducts2(String code);

    Call<List<ProductItem>> getProducts3(String code);

    Call<List<ProductItem>> getProducts4(String code);

    Call<List<ProductItem>> SearchProducts(String txt);

    Call<List<RateModel>> getRating();

    Call<List<MarketModel>> getMarkets();

    Call<List<FestivalModel>> getFestivals();

    Call<ResponseBody> sendComment(/*String id, */String bazarname, String number, String nazar);

    Call<ResponseBody> registerClub(/*String id, */String name, String family, String mobile);

    Call<List<EducationModel>> getEducation(String code);
}
