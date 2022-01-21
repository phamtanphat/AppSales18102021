package com.example.appsales18102021.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.appsales18102021.MyApplication;
import com.example.appsales18102021.di.activities.ActivityBuilderModule;
import com.example.appsales18102021.di.viewmodels.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                NetworkModule.class,
                ViewModelFactoryModule.class,
                SharePrefModule.class
        }
)
public interface AppComponent extends AndroidInjector<MyApplication> {

    SharedPreferences getSharePreference();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}