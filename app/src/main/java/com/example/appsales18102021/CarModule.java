package com.example.appsales18102021;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModule {
//
//    @Provides
//    Wheel provideWheel(Context context) {
//        return new Wheel(4,context);
//    }
//
//    @Provides
//    Engine provideEngine() {
//        return new Engine("Động cơ 4 mã lực");
//    }

    @Provides
    Car provideCar(Engine engine, Wheel wheel) {
        return new Car(engine, wheel);
    }
}
