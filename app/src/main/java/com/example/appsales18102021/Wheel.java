package com.example.appsales18102021;

import android.content.Context;

import javax.inject.Inject;

public class Wheel {
    int quantily;

    @Inject
    public Wheel(int quantily, Context context) {
        this.quantily = quantily;
    }
}
