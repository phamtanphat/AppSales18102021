package com.example.appsales18102021.di.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.di.ViewModelKey;
import com.example.appsales18102021.presentation.viewmodel.OrderViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class OrderViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OrderViewModel.class)
    abstract public ViewModel bindOrderViewModel(OrderViewModel orderViewModel);
}
