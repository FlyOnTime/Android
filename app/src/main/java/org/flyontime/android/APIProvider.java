package org.flyontime.android;

import org.flyontime.android.model.service.FlyOnTimeAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JW on 24.04.2017.
 */

public class APIProvider {

    // TODO: What APIs to use?

    private static APIProvider instance = null;
    private FlyOnTimeAPI api = createAPIInstance();


    private APIProvider() {

    }

    public static APIProvider getInstance() {
        if (instance == null) {
            instance = new APIProvider();
        }
        return instance;
    }

    private FlyOnTimeAPI createAPIInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FlyOnTimeAPI.URL)
                .build();
        return retrofit.create(FlyOnTimeAPI.class);
    }

    public FlyOnTimeAPI getAPI() {
        return api;
    }
}
