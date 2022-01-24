package com.example.appsales18102021.data.datasource.remote.api;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequest {

    @POST("api/v1/user/sign-in")
    Call<AppResource<UserModel>> signIn(@Body UserModel userModel);
}
