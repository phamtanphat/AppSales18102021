package com.example.appsales18102021.presentation.repositories;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.datasource.remote.api.ApiRequest;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.data.model.OrderModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;
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

    public Call<AppResource<String>> updateOrder(String orderId , String foodId , int quantity){
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("orderId", orderId);
        jsonParams.put("foodId", foodId);
        jsonParams.put("quantity", quantity);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
        return apiRequest.updateOrder(body);
    }
}
