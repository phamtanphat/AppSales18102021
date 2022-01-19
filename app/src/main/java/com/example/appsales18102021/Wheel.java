package com.example.appsales18102021;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

public class Wheel {
    int quantily;

    @Inject
    public Wheel(int quantily, Context context) {
        this.quantily = quantily;
        Log.d("BBB",context.toString());
    }
}
