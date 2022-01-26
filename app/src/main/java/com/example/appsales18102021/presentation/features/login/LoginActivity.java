package com.example.appsales18102021.presentation.features.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.example.appsales18102021.R;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.features.home.HomeActivity;
import com.example.appsales18102021.presentation.features.register.RegisterActivity;
import com.example.appsales18102021.presentation.viewmodel.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    AuthViewModel mAuthViewModel;

    Button mBtnSignIn;
    TextInputEditText mTxtInputEdittextEmail,mTxtInputEdittextPassword;
    TextView mTvRegister;
    View mLoading;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnSignIn = findViewById(R.id.buttonSignIn);
        mTxtInputEdittextEmail = findViewById(R.id.textEditEmail);
        mTxtInputEdittextPassword = findViewById(R.id.textEditPassword);
        mTvRegister = findViewById(R.id.textViewRegister);
        mLoading = findViewById(R.id.includeLoading);
        mToolbar = findViewById(R.id.toolbarLogin);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login System");

        mAuthViewModel.getUserModelData().observe(this, new Observer<AppResource<UserModel>>() {
            @Override
            public void onChanged(AppResource<UserModel> userModelAppResource) {
                if (userModelAppResource != null){
                    switch (userModelAppResource.status){
                        case LOADING:
                            mLoading.setVisibility(View.VISIBLE);
                            break;
                        case ERROR:
                            mLoading.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, userModelAppResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            mLoading.setVisibility(View.GONE);
                            Intent view = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(view);
                            break;
                    }
                }
            }
        });


        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTxtInputEdittextEmail.getText().toString();
                String password = mTxtInputEdittextPassword.getText().toString();

                mAuthViewModel.signIn(new UserModel(email,password));

            }
        });


        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(view);
            }
        });

    }
}