package com.example.hocretrofit.API;

import com.example.hocretrofit.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface UserApiService {
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:").create();
    UserApiService USER_API_SERVICE =new Retrofit.Builder()
            .baseUrl("http://dummyapi.io/")
            .addConverterFactory(GsonConverterFactory
                    .create(gson))
            .build().create(UserApiService.class);
    @Headers("app-id: 619c85e96db4c337a49c8013")
    @GET("data/v1/user/60d0fe4f5311236168a109ca")
    Call<User>getUser();
}
