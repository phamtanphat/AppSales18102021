package com.example.appsales18102021.di.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.di.ViewModelKey;
import com.example.appsales18102021.presentation.viewmodel.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class FoodViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public abstract ViewModel bindFoodViewModel(HomeViewModel homeViewModel);
}
