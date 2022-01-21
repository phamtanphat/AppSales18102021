package com.example.appsales18102021.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.appsales18102021.common.Constant;
import com.example.appsales18102021.data.datasource.local.SharePref;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SharePrefModule {

    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(Constant.KEY_NAME_SHARE_PREF,Context.MODE_PRIVATE);
    }
}
