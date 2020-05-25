package com.shahrdari.remote.repository;


import com.shahrdari.models.BannerModel;
import com.shahrdari.models.ProductItem;

import java.util.List;

import retrofit2.Call;

//import io.reactivex.Observable;

public interface Repository {

    /*
     * Get list of popular Experts
     */
    /*  Observable<ExpertItem> getAllExperts(Map<String, String> params);*/

    Call<List<BannerModel>> getBanners();

    Call<List<ProductItem>> getProducts1(String code);

    Call<List<ProductItem>> getProducts2(String code);

    Call<List<ProductItem>> getProducts3(String code);

    Call<List<ProductItem>> getProducts4(String code);


}
