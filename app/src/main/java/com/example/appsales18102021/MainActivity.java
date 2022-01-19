package com.example.appsales18102021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Car car;

    Button mBtnNavigateScreen2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNavigateScreen2 = findViewById(R.id.buttonNavigateScreen2);

        mBtnNavigateScreen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });


        DaggerMainComponent
                .builder()
                .context(getApplicationContext())
                .build()
                .injectMain(this);

        Log.d("BBB", car.toString());
    }
}