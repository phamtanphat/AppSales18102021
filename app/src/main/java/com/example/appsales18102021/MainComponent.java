package com.example.appsales18102021;

import dagger.Component;

@Component(modules = CarModule.class)
public interface MainComponent {

   void injectMain(MainActivity mainActivity);
}
