package com.example.appsales18102021.presentation.features.login;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.appsales18102021.R;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.viewmodel.AuthViewModel;
import com.example.appsales18102021.presentation.viewmodel.ViewModelFactoryProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    AuthViewModel mAuthViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuthViewModel.signIn(new UserModel("demo@gmail.com","12345678"));
    }
}