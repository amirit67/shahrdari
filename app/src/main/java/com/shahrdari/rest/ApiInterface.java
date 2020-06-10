package com.shahrdari.rest;

import com.shahrdari.models.BannerModel;
import com.shahrdari.models.ProductItem;
import com.shahrdari.models.RateModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    /*@POST(BuildConfig.UpdateApp)
    Call<ResponseBody> UpdateApp();

    @POST("Api/v1/register/")
    Observable<RegisterModel> InvestamApplicantRegister(@Body UserObject user);

    @GET(BuildConfig.getExpertProfile)
    Call<ProfileItem> getExpertProfile(@Path("id") String id);

    @POST(BuildConfig.changePassword)
    Observable<ResponseBody> ChangePassword(@Body UserObject user);

    @FormUrlEncoded
    @POST(BuildConfig.GetAllQuestion)
    Call<QuestionItem> GetAllQuestion(@FieldMap Map<String, String> data);

    @Multipart
    @POST(BuildConfig.applicantQuestion)
    Observable<ResponseBody> Question(@Part MultipartBody.Part[] surveyImage, @PartMap Map<String, RequestBody> params);*/

    @GET("api_viewpicsforbanner.aspx")
    Call<List<BannerModel>> GetBanner();

    @GET("api_view1")
    Call<List<ProductItem>> GetProductsGroup1(@Query("cod") String cod);

    @GET("api_view2")
    Call<List<ProductItem>> GetProductsGroup2(@Query("cod") String cod);

    @GET("api_view3")
    Call<List<ProductItem>> GetProductsGroup3(@Query("cod") String cod);

    @GET("api_view4")
    Call<List<ProductItem>> GetProductsGroup4(@Query("cod") String cod);

    @GET("api_viewlistnerkhnameh")
    Call<List<RateModel>> GetRating();

}