package com.example.appsales18102021;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    Engine engine;
    Wheel wheel;

    @Inject
    public Car(Engine engine, Wheel wheel) {
        this.engine = engine;
        this.wheel = wheel;
    }

    void showCarInfo(){
        Log.d("BBB","Engine : " + engine.name);
        Log.d("BBB","Wheel : " + wheel.quantily);
    }
}
