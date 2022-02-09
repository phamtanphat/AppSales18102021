package com.example.appsales18102021.data.datasource.remote.api;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.CartModel;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.data.model.OrderModel;
import com.example.appsales18102021.data.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {

    @POST("api/v1/user/sign-in")
    Call<AppResource<UserModel>> signIn(@Body UserModel userModel);

    @POST("api/v1/user/sign-up")
    Call<AppResource<UserModel>> signUp(@Body UserModel userModel);


    @GET("api/v1/food/list/0/10")
    Call<AppResource<List<FoodModel>>> fetchListFoods();

    @GET("api/v1/order/count/shopping-cart")
    Call<AppResource<CartModel>> fetchTotalCart();

    @POST("api/v1/order/add-to-cart")
    Call<AppResource<CartModel>> addToCart(@Body FoodModel foodModel);

    @GET("api/v1/order/shopping-cart")
    Call<AppResource<OrderModel>> fetchOrder();
}
