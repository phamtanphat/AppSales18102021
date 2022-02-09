package com.example.appsales18102021.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.OrderModel;
import com.example.appsales18102021.presentation.repositories.OrderRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<AppResource<OrderModel>> orderData = new MediatorLiveData<>();
    private OrderRepository orderRepository;

    @Inject
    public OrderViewModel(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public LiveData<AppResource<OrderModel>> getUserModelData(){
        return orderData;
    }

    public void fetchOrder(){
        orderData.setValue(new AppResource.Loading<>(null));
        orderRepository.fetchOrder().enqueue(new Callback<AppResource<OrderModel>>() {
            @Override
            public void onResponse(Call<AppResource<OrderModel>> call, Response<AppResource<OrderModel>> response) {
                if (response.isSuccessful()){
                    AppResource<OrderModel> data = response.body();
                    if (data != null && data.data != null){
                        orderData.setValue(new AppResource.Success(data.data));
                    }
                }
                if (response.errorBody() != null){
                    ResponseBody errorBody = response.errorBody();
                    try {
                        JSONObject jsonObject = new JSONObject(errorBody.string());
                        String message = jsonObject.getString("message");
                        orderData.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AppResource<OrderModel>> call, Throwable t) {
                orderData.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }


}
