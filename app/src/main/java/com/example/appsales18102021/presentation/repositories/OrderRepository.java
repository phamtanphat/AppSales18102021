package com.example.appsales18102021.presentation.repositories;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.datasource.remote.api.ApiRequest;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.data.model.OrderModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class OrderRepository {
    private ApiRequest apiRequest;

    @Inject
    public OrderRepository(ApiRequest apiRequest){
        this.apiRequest = apiRequest;
    }

    public Call<AppResource<OrderModel>> fetchOrder(){
        return apiRequest.fetchOrder();
    }
}
