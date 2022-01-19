package com.example.appsales18102021;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = CarModule.class)
public interface MainComponent {

   void injectMain(MainActivity mainActivity);
   void injectMain2(MainActivity2 mainActivity2);

   @Component.Builder
   interface Builder{

      @BindsInstance
      Builder context(Context context);

      MainComponent build();
   }
}
