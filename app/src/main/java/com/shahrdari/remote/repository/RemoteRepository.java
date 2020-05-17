package com.shahrdari.remote.repository;

import com.shahrdari.MyApp;
import com.shahrdari.rest.ApiInterface;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RemoteRepository implements Repository {

    @Inject
    Retrofit retrofit;
    ApiInterface apiInterface;

    public RemoteRepository() {
        MyApp.getmainComponent().Inject(this);
        apiInterface = retrofit.create(ApiInterface.class);
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
