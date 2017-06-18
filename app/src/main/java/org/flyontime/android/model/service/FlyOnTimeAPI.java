package org.flyontime.android.model.service;

import org.flyontime.android.model.data.FlyOnTime.MainRequestBody;
import org.flyontime.android.model.data.FlyOnTime.TravelInfo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by JW on 24.04.2017.
 */

public interface FlyOnTimeAPI {

    // TODO: Configure for the used APIs & Endpoints

    String URL = "https://blooming-taiga-70606.herokuapp.com/backend/";

    @GET("travelInfo")
    Observable<TravelInfo> getTravelInfo();

    @POST("travelInfo")
    Observable<TravelInfo> getTravelInfoPost(@Body MainRequestBody requestBody);

}
