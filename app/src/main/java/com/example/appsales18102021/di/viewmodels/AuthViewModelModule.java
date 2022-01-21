package com.example.appsales18102021.di.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.di.ViewModelKey;
import com.example.appsales18102021.presentation.viewmodel.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
