package com.example.appsales18102021.presentation.features.home;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsales18102021.R;
import com.example.appsales18102021.presentation.adapter.FoodAdapter;

import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity {


    Toolbar mToolbar;
    FoodAdapter mFoodAdapter;
    RecyclerView mRcvFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbarHome);
        mRcvFood = findViewById(R.id.recyclerViewHome);

    }
}