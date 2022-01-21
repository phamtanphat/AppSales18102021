package com.example.appsales18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity2 extends DaggerAppCompatActivity {

    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d("BBB",car + "");
    }
}