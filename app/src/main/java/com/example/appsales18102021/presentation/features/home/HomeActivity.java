package com.example.appsales18102021.presentation.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsales18102021.R;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.presentation.adapter.FoodAdapter;
import com.example.appsales18102021.presentation.features.login.LoginActivity;
import com.example.appsales18102021.presentation.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity {

    @Inject
    FoodViewModel mFoodViewModel;

    Toolbar mToolbar;
    FoodAdapter mFoodAdapter;
    RecyclerView mRcvFood;
    View mLoading;
    List<FoodModel> mListFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbarHome);
        mRcvFood = findViewById(R.id.recyclerViewHome);
        mLoading = findViewById(R.id.includeLoading);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Foods");

        mListFoods = new ArrayList<>();
        mFoodAdapter = new FoodAdapter();

        mRcvFood.setHasFixedSize(true);
        mRcvFood.setAdapter(mFoodAdapter);

        mFoodViewModel.getListFoods().observe(this, new Observer<AppResource<List<FoodModel>>>() {
            @Override
            public void onChanged(AppResource<List<FoodModel>> listAppResource) {
                if (listAppResource != null){
                    switch (listAppResource.status){
                        case LOADING:
                            mLoading.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            mLoading.setVisibility(View.GONE);
                            Toast.makeText(HomeActivity.this, listAppResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            mLoading.setVisibility(View.GONE);
                            mListFoods = listAppResource.data;
                            mFoodAdapter.updateListFoodModels(mListFoods);
                            break;
                    }
                }
            }
        });

        mFoodViewModel.fetchListFoods();

    }
}