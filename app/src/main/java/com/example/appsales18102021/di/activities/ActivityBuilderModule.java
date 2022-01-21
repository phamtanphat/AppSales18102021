package com.example.appsales18102021.di.activities;

import com.example.appsales18102021.presentation.features.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    public abstract LoginActivity bindContributeLoginActivity();
}
