package com.example.appsales18102021.presentation.repositories;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.datasource.remote.api.ApiRequest;
import com.example.appsales18102021.data.model.CartModel;
import com.example.appsales18102021.data.model.UserModel;

import javax.inject.Inject;

import retrofit2.Call;

public class CartRepository {
    private ApiRequest apiRequest;

    @Inject
    public CartRepository(ApiRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public Call<AppResource<CartModel>> fetchTotalCart(){
        return apiRequest.fetchTotalCart();
    }

}
