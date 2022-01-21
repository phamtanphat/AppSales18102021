package com.example.appsales18102021.di.viewmodels;

import androidx.lifecycle.ViewModelProvider;

import com.example.appsales18102021.presentation.viewmodel.ViewModelFactoryProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelFactoryModule {
    @Singleton
    @Provides
    public ViewModelProvider.Factory bindViewModelFactory(ViewModelFactoryProvider viewModelFactory){
        return viewModelFactory;
    }
}
