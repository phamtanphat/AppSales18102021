package com.example.appsales18102021.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.repositories.AuthRepository;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel{
//    private MutableLiveData<>
    private AuthRepository authRepository;

    @Inject
    public AuthViewModel(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public void signIn(UserModel userModel){
        authRepository.signIn(userModel).enqueue(new Callback<AppResource<UserModel>>() {
            @Override
            public void onResponse(Call<AppResource<UserModel>> call, Response<AppResource<UserModel>> response) {
                Log.d("BBB",response.body().toString());
            }

            @Override
            public void onFailure(Call<AppResource<UserModel>> call, Throwable t) {
                Log.d("BBB",t.getMessage().toString());
            }
        });
    }
}
