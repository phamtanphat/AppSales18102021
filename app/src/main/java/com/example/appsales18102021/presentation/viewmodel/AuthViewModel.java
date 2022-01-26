package com.example.appsales18102021.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.common.Constant;
import com.example.appsales18102021.data.datasource.local.SharePref;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.repositories.AuthRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel{
//    private MutableLiveData<>

    private MutableLiveData<AppResource<UserModel>> userModelLiveData = new MediatorLiveData<>();
    private AuthRepository authRepository;
    private SharePref sharePref;

    @Inject
    public AuthViewModel(AuthRepository authRepository , SharePref sharePref){
        this.authRepository = authRepository;
        this.sharePref = sharePref;
    }

    public LiveData<AppResource<UserModel>> getUserModelData(){
        return userModelLiveData;
    }

    public void signIn(UserModel userModel){
        userModelLiveData.setValue(new AppResource.Loading<>(null));
        authRepository.signIn(userModel).enqueue(new Callback<AppResource<UserModel>>() {
            @Override
            public void onResponse(Call<AppResource<UserModel>> call, Response<AppResource<UserModel>> response) {
                if (response.isSuccessful()){
                    AppResource<UserModel> data = response.body();
                    if (data != null && data.data != null){
                        sharePref.setToken(Constant.KEY_TOKEN,data.data.getToken());
                        userModelLiveData.setValue(new AppResource.Success(data.data));
                    }
                }
                if (response.errorBody() != null){
                    ResponseBody errorBody = response.errorBody();
                    try {
                        JSONObject jsonObject = new JSONObject(errorBody.string());
                        String message = jsonObject.getString("message");
                        userModelLiveData.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AppResource<UserModel>> call, Throwable t) {
                userModelLiveData.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }

    public void signUp(UserModel userModel){
        userModelLiveData.setValue(new AppResource.Loading<>(null));
        authRepository.signUp(userModel).enqueue(new Callback<AppResource<UserModel>>() {
            @Override
            public void onResponse(Call<AppResource<UserModel>> call, Response<AppResource<UserModel>> response) {
                if (response.isSuccessful()){
                    AppResource<UserModel> data = response.body();
                    if (data != null && data.data != null){
                        userModelLiveData.setValue(new AppResource.Success(data.data));
                    }
                }
                if (response.errorBody() != null){
                    ResponseBody errorBody = response.errorBody();
                    try {
                        JSONObject jsonObject = new JSONObject(errorBody.string());
                        String message = jsonObject.getString("message");
                        userModelLiveData.setValue(new AppResource.Error<>(message));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AppResource<UserModel>> call, Throwable t) {
                userModelLiveData.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }
}
