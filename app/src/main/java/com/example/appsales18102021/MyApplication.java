package com.example.appsales18102021;

import android.app.Application;

public class MyApplication extends Application {

    MainComponent mainComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent
                .builder()
                .context(this)
                .build();

        mainComponent.injectMain(this);


    }
}
