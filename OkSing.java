package com.company;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class OkSing {

    private static OkHttpClient instance = null;

    private OkSing() {
    }

    public static synchronized OkHttpClient getInstance() {
        if (instance == null) {
            instance = new OkHttpClient();
        }
        return instance;
    }
}
