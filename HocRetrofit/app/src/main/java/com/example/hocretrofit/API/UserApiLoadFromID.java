package com.example.hocretrofit.API;

import com.example.hocretrofit.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserApiLoadFromID {
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:").create();
    UserApiLoadFromID USER_API_SERVICE =new Retrofit.Builder()
            .baseUrl("http://dummyapi.io/")
            .addConverterFactory(GsonConverterFactory
                    .create(gson))
            .build().create(UserApiLoadFromID.class);
    @Headers("app-id: 61b2cb0f6679f1c43b8b1753")
    @GET("data/v1/user/{id}")
    Call<User> getUser(@Path("id") String id);
}
