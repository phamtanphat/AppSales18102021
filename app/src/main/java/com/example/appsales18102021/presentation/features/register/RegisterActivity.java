package com.example.appsales18102021.presentation.features.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.example.appsales18102021.R;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.viewmodel.AuthViewModel;
import com.google.android.material.textfield.TextInputEditText;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class RegisterActivity extends DaggerAppCompatActivity {

    @Inject
    AuthViewModel mAuthViewModel;

    Toolbar mToolbar;
    TextInputEditText mEdtName, mEdtLocation,mEdtEmail,mEdtPhone,mEdtPassword;
    Button mBtnSignUp;
    View mLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mToolbar = findViewById(R.id.toolbarRegister);
        mEdtName = findViewById(R.id.textEditName);
        mEdtLocation = findViewById(R.id.textEditLocation);
        mEdtEmail = findViewById(R.id.textEditEmail);
        mEdtPhone = findViewById(R.id.textEditPhone);
        mEdtPassword = findViewById(R.id.textEditPassword);
        mBtnSignUp = findViewById(R.id.buttonSignUp);
        mLoading = findViewById(R.id.containerLoading);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Register");

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
                            Toast.makeText(RegisterActivity.this, userModelAppResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        case SUCCESS:
                            mLoading.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                    }
                }
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEdtName.getText().toString();
                String email = mEdtEmail.getText().toString();
                String location = mEdtLocation.getText().toString();
                String phone = mEdtPhone.getText().toString();
                String password = mEdtPassword.getText().toString();

                mAuthViewModel.signUp(new UserModel(name,email,password,phone,location));
            }
        });
    }
}