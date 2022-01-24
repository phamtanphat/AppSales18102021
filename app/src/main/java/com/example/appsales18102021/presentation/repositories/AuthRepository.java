package com.example.appsales18102021.presentation.repositories;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.datasource.remote.api.ApiRequest;
import com.example.appsales18102021.data.model.UserModel;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthRepository {
    private ApiRequest apiRequest;

    @Inject
    public AuthRepository(ApiRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public Call<AppResource<UserModel>> signIn(UserModel userModel){
        return apiRequest.signIn(userModel);
    }

    public Call<AppResource<UserModel>> signUp(UserModel userModel){
        return apiRequest.signUp(userModel);
    }
}

