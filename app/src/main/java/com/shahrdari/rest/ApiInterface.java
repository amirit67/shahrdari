package com.shahrdari.rest;

import com.shahrdari.BuildConfig;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
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
    Observable<ResponseBody> Question(@Part MultipartBody.Part[] surveyImage, @PartMap Map<String, RequestBody> params);

    @GET("Api/v1/Get/Poll/all")
    Call<com.sanatyar.investam.model.survey.pollList.SurveyItem> GetPollList(@Query("Skip") int skip);*/

}