package com.company;

import okhttp3.Request;

public class RequestSingleton {

    private static Request.Builder instance = null;

    private RequestSingleton() {
    }

    public static synchronized Request.Builder getInstance() {
        if (instance == null) {
            instance = new Request.Builder();
        }
        return instance;
    }
}
