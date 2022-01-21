package com.example.appsales18102021;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMainComponent.builder().context(this).build();
    }
}
