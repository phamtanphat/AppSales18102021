package com.example.appsales18102021;

import javax.inject.Inject;

public class Engine {
    String name;

    @Inject
    public Engine(String name) {
        this.name = name;
    }
}
