package com.example.appsales18102021.presentation.features.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    String orderId = "";
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

        // get data
        orderId = getIntent().getStringExtra("orderId");


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

        mOrderViewModel.getCodeUpdateData().observe(this, new Observer<AppResource<String>>() {
            @Override
            public void onChanged(AppResource<String> messageResponse) {
                if (messageResponse != null){
                    switch (messageResponse.status){
                        case LOADING:
                            mLoading.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            mLoading.setVisibility(View.GONE);
                            Toast.makeText(OrderActivity.this, messageResponse.message, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            mLoading.setVisibility(View.GONE);
                            Log.d("BBB",messageResponse.data);
                            break;
                    }
                }
            }
        });
        mOrderViewModel.fetchOrder();

        mOrderAdapter.setOnListenerCartItem(new OrderAdapter.OnListenerCartItem() {
            @Override
            public void onPlus(int position) {
                if (position >= 0 && orderId.length() > 0){
                    mOrderViewModel.updateOrder(orderId,mOrderAdapter.getList().get(position).getFoodId(),mOrderAdapter.getList().get(position).getQuantity() + 1);
                }

            }

            @Override
            public void onMinus(int position) {

            }

            @Override
            public void onDelete(int position) {

            }
        });
    }
}