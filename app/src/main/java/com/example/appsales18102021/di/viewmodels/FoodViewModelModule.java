package com.example.appsales18102021.di.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.di.ViewModelKey;
import com.example.appsales18102021.presentation.viewmodel.FoodViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class FoodViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel.class)
    public abstract ViewModel bindFoodViewModel(FoodViewModel foodViewModel);
}
