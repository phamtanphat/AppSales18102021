package com.example.appsales18102021.presentation.features.cart;

import android.os.Bundle;

import com.example.appsales18102021.R;

import dagger.android.support.DaggerAppCompatActivity;

public class CartActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }
}