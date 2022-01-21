package com.example.appsales18102021;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        CarModule.class
})
public interface MainComponent extends AndroidInjector<MyApplication> {

   @Component.Builder
   interface Builder{

      @BindsInstance
      Builder context(Context context);

      MainComponent build();
   }
}
