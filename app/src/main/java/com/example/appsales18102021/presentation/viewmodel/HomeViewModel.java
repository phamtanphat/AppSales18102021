package com.example.appsales18102021.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.common.Constant;
import com.example.appsales18102021.data.datasource.local.SharePref;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.CartModel;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.repositories.AuthRepository;
import com.example.appsales18102021.presentation.repositories.CartRepository;
import com.example.appsales18102021.presentation.repositories.FoodRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<AppResource<List<FoodModel>>> listFoods = new MediatorLiveData<>();
    private MutableLiveData<AppResource<CartModel>> cartMutable = new MediatorLiveData<>();
    private CartRepository cartRepository;
    private FoodRepository foodRepository;

    @Inject
    public HomeViewModel(FoodRepository foodRepository, CartRepository cartRepository){
        this.foodRepository = foodRepository;
        this.cartRepository = cartRepository;
    }

    public LiveData<AppResource<List<FoodModel>>> getListFoods(){
        return listFoods;
    }


    public LiveData<AppResource<CartModel>> getTotalCart(){
        return cartMutable;
    }

    public void fetchListFoods(){
        listFoods.setValue(new AppResource.Loading<>(null));
        foodRepository.fetchListFoods().enqueue(new Callback<AppResource<List<FoodModel>>>() {
            @Override
            public void onResponse(Call<AppResource<List<FoodModel>>> call, Response<AppResource<List<FoodModel>>> response) {
                if (response.isSuccessful()){
                    AppResource<List<FoodModel>> data = response.body();
                    if (data != null && data.data != null){
                        listFoods.setValue(new AppResource.Success(data.data));
                    }
                }
                if (response.errorBody() != null){
                    ResponseBody errorBody = response.errorBody();
                    try {
                        JSONObject jsonObject = new JSONObject(errorBody.string());
                        String message = jsonObject.getString("message");
                        listFoods.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AppResource<List<FoodModel>>> call, Throwable t) {
                listFoods.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }



    public void fetchTotalCart(){
        cartMutable.setValue(new AppResource.Loading<>(null));
        cartRepository.fetchTotalCart().enqueue(new Callback<AppResource<CartModel>>() {
            @Override
            public void onResponse(Call<AppResource<CartModel>> call, Response<AppResource<CartModel>> response) {
                if (response.isSuccessful()){
                    AppResource<CartModel> data = response.body();
                    if (data != null && data.data != null){
                        cartMutable.setValue(new AppResource.Success(data.data));
                    }
                }
                if (response.errorBody() != null){
                    ResponseBody errorBody = response.errorBody();
                    if (response.code() == 404){
                        cartMutable.setValue(new AppResource.Error<>("404"));
                    }else if (response.code() == 401){
                        cartMutable.setValue(new AppResource.Error<>("401"));
                    }else{
                        try {
                            JSONObject jsonObject = new JSONObject(errorBody.string());
                            String message = jsonObject.getString("message");
                            cartMutable.setValue(new AppResource.Error<>(message));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }

            @Override
            public void onFailure(Call<AppResource<CartModel>> call, Throwable t) {
                cartMutable.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }
}
