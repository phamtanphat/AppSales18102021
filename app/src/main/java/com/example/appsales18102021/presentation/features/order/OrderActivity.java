package com.example.appsales18102021.presentation.features.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsales18102021.R;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.OrderModel;
import com.example.appsales18102021.presentation.adapter.OrderAdapter;
import com.example.appsales18102021.presentation.features.home.HomeActivity;
import com.example.appsales18102021.presentation.features.login.LoginActivity;
import com.example.appsales18102021.presentation.viewmodel.OrderViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class OrderActivity extends DaggerAppCompatActivity {

    @Inject
    OrderViewModel mOrderViewModel;


    Toolbar mToolbar;
    OrderAdapter mOrderAdapter;
    RecyclerView mRcvOrder;
    View mLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mToolbar = findViewById(R.id.toolbarOrder);
        mLoading = findViewById(R.id.layoutLoading);
        mRcvOrder = findViewById(R.id.recyclerViewOrder);
        mLoading = findViewById(R.id.includeLoading);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Order");

        mOrderAdapter = new OrderAdapter();
        mRcvOrder.setHasFixedSize(true);
        mRcvOrder.setAdapter(mOrderAdapter);

        mOrderViewModel.getUserModelData().observe(this, new Observer<AppResource<OrderModel>>() {
            @Override
            public void onChanged(AppResource<OrderModel> orderModelAppResource) {
                if (orderModelAppResource != null){
                    switch (orderModelAppResource.status){
                        case LOADING:
                            mLoading.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            mLoading.setVisibility(View.GONE);
                            Toast.makeText(OrderActivity.this, orderModelAppResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            mLoading.setVisibility(View.GONE);
                            mOrderAdapter.updateCart(orderModelAppResource.data.getItems());
                            break;
                    }
                }
            }
        });
        mOrderViewModel.fetchOrder();
    }
}