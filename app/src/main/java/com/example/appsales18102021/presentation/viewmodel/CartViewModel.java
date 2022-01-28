package com.example.appsales18102021.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsales18102021.common.Constant;
import com.example.appsales18102021.data.datasource.local.SharePref;
import com.example.appsales18102021.data.datasource.remote.AppResource;
import com.example.appsales18102021.data.model.CartModel;
import com.example.appsales18102021.data.model.UserModel;
import com.example.appsales18102021.presentation.repositories.AuthRepository;
import com.example.appsales18102021.presentation.repositories.CartRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {

}
