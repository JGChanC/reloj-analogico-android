package com.example.jgchan.examenmoviles;

import android.app.Application;

public class MyApplication extends Application {
    BitmapCache cache = null;

    @Override
    public void onCreate() {
        super.onCreate();
        cache = new BitmapCache(BitmapCache.getCacheSize());
    }

    public BitmapCache getCache() {
        return cache;
    }
}