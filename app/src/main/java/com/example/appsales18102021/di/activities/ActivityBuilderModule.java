package com.example.appsales18102021.di.activities;

import com.example.appsales18102021.di.viewmodels.AuthViewModelModule;
import com.example.appsales18102021.presentation.features.home.HomeActivity;
import com.example.appsales18102021.presentation.features.login.LoginActivity;
import com.example.appsales18102021.presentation.features.register.RegisterActivity;
import com.example.appsales18102021.presentation.features.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = AuthViewModelModule.class
    )
    public abstract LoginActivity bindContributeLoginActivity();

    @ContributesAndroidInjector(
            modules = AuthViewModelModule.class
    )
    public abstract RegisterActivity bindContributeRegisterActivity();

    @ContributesAndroidInjector
    public abstract SplashActivity bindContributeSplashActivity();

    @ContributesAndroidInjector
    public abstract HomeActivity bindContributeHomeActivity();
}
