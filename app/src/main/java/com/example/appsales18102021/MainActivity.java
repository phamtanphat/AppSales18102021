package com.example.appsales18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Car car;

    @Inject
    Wheel wheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent
                .builder()
                .context(getApplicationContext())
                .bindWheel(new Wheel(8,getApplicationContext()))
                .bindEngine(new Engine("8 mã lực"))
                .build()
                .injectMain(this);

        car.showCarInfo();
        Log.d("BBB",wheel.quantily + "");
    }
}