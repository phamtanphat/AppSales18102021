package com.example.appsales18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity2 extends AppCompatActivity {

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DaggerMainComponent
                .builder()
                .context(getApplicationContext())
                .build()
                .injectMain2(this);

        Log.d("BBB",car.toString());
    }
}