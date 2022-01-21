package com.example.appsales18102021.di;

import android.content.SharedPreferences;

import com.example.appsales18102021.common.Constant;
import com.example.appsales18102021.data.datasource.remote.api.ApiRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    OkHttpClient provideClient(SharedPreferences sharedPreferences){
        return new OkHttpClient.Builder()
                .readTimeout(30 , TimeUnit.SECONDS)
                .writeTimeout(30 , TimeUnit.SECONDS)
                .connectTimeout(30 , TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String token = sharedPreferences.getString(Constant.KEY_TOKEN,"");
                        Request newRequest=chain.request().newBuilder()
                                .header("Authorization","Bearer "+ token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson , OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiRequest provideRequest(Retrofit retrofit){
        return retrofit.create(ApiRequest.class);
    }

}
