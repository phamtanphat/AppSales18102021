package com.example.appsales18102021.presentation.repositories;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.datasource.remote.api.ApiRequest;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.data.model.UserModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class FoodRepository {

    private ApiRequest apiRequest;

    @Inject
    public FoodRepository(ApiRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public Call<AppResource<List<FoodModel>>> fetchListFoods(){
        return apiRequest.fetchListFoods();
    }

}
