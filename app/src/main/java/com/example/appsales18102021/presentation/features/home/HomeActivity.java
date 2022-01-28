package com.example.appsales18102021.presentation.features.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsales18102021.R;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.CartModel;
import com.example.appsales18102021.data.model.FoodModel;
import com.example.appsales18102021.presentation.adapter.FoodAdapter;
import com.example.appsales18102021.presentation.features.login.LoginActivity;
import com.example.appsales18102021.presentation.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class HomeActivity extends DaggerAppCompatActivity {

    @Inject
    HomeViewModel mHomeViewModel;

    Toolbar mToolbar;
    FoodAdapter mFoodAdapter;
    RecyclerView mRcvFood;
    View mLoading;
    List<FoodModel> mListFoods;
    TextView mTxtCountCart;

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

        mFoodAdapter.setOnFoodItemListener(new FoodAdapter.OnFoodItemListener() {
            @Override
            public void onClick(int position) {
                mHomeViewModel.addToCart(new FoodModel(mFoodAdapter.getList().get(position).getFoodId()));
            }
        });

        mHomeViewModel.getListFoods().observe(this, new Observer<AppResource<List<FoodModel>>>() {
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

        mHomeViewModel.getTotalCart().observe(this, new Observer<AppResource<CartModel>>() {
            @Override
            public void onChanged(AppResource<CartModel> cartModelAppResource) {
                if (cartModelAppResource != null){
                    switch (cartModelAppResource.status){
                        case LOADING:
                            mLoading.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            mLoading.setVisibility(View.GONE);
                            if (cartModelAppResource.message.equals("401")){
                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else if(cartModelAppResource.message.equals("404")){
                                setTotalCart(0);
                            }else{
                                Toast.makeText(HomeActivity.this, cartModelAppResource.message, Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case SUCCESS:
                            mLoading.setVisibility(View.GONE);
                            setTotalCart(cartModelAppResource.data.getTotal());
                            break;
                    }
                }
            }
        });



        mHomeViewModel.fetchTotalCart();
        mHomeViewModel.fetchListFoods();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();

        mTxtCountCart = actionView.findViewById(R.id.textViewCart);


        return true;
    }

    private void setTotalCart(int total){
        if (total <= 0){
            mTxtCountCart.setVisibility(View.GONE);
        }else{
            mTxtCountCart.setVisibility(View.VISIBLE);
            mTxtCountCart.setText(total+"");
        }
    }
}