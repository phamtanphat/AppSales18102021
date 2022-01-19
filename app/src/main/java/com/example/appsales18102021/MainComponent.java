package com.example.appsales18102021;

import android.content.Context;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = CarModule.class)
public interface MainComponent {

   void injectMain(MainActivity mainActivity);

   @Component.Builder
   interface Builder{

      @BindsInstance
      Builder context(Context context);

      @BindsInstance
      Builder bindWheel(Wheel wheel);

      @BindsInstance
      Builder bindEngine(Engine engine);

      MainComponent build();
   }
}
