package com.example.data.repository.remote.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Banty on 15/04/18.
 */
public class RetrofitClient {
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private static Retrofit INSTANCE = null;

    public static ApiInterface getRetrofitClient() {
            if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return INSTANCE.create(ApiInterface.class);
    }
}
