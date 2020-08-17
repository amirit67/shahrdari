package com.shahrdari.rest;

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
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @POST("api_updateapp.aspx")
    Call<ResponseBody> UpdateApp();

    @GET("api_viewpicsforbanner.aspx")
    Call<List<BannerModel>> GetBanner();

    @GET("api-weather")
    Call<List<WeatherModel>> GetWeather();

    @GET("api_view1")
    Call<List<ProductItem>> GetProductsGroup1(@Query("cod") String cod);

    @GET("api_view2")
    Call<List<ProductItem>> GetProductsGroup2(@Query("cod") String cod);

    @GET("api_view3")
    Call<List<ProductItem>> GetProductsGroup3(@Query("cod") String cod);

    @GET("api_view4")
    Call<List<ProductItem>> GetProductsGroup4(@Query("cod") String cod);

    @GET("api-searchitems")
    Call<List<ProductItem>> SearchProducts(@Query("searchstr") String searchstr);

    @GET("api_viewlistnerkhnameh")
    Call<List<RateModel>> GetRating();

    @GET("api_viewlistbazarmayadin")
    Call<List<MarketModel>> GetMarkets();

    @GET("api-festivals")
    Call<List<FestivalModel>> GetFestivals();

    @GET("input-nazarat.aspx")
    Call<ResponseBody> SendComment(/*@Query("id") String id,*/ @Query("bazarname") String bazarname, @Query("number") String number, @Query("nazar") String nazar);

    @GET("input-customers.aspx")
    Call<ResponseBody> RegisterClub(/*@Query("id") String id,*/ @Query("name") String name, @Query("famili") String family, @Query("mobile") String mobile);

    @GET("api_education")
    Call<List<EducationModel>> GetEducation(@Query("cod") String cod);

}