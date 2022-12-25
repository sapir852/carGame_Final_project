package com.example.cargame;

import android.app.Application;

import com.google.gson.Gson;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySignal.init(this);
        MySPV3.init(this);



    }


}
