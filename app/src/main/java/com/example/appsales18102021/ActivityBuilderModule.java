package com.example.appsales18102021;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    public abstract MainActivity bindContributeMainActivity();

    @ContributesAndroidInjector
    public abstract MainActivity2 bindContributeMainActivity2();
}
