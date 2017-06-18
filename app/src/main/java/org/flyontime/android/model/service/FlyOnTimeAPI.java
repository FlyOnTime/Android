package org.flyontime.android.model.service;

import org.flyontime.android.model.data.FlyOnTime.TravelInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by JW on 24.04.2017.
 */

public interface FlyOnTimeAPI {

    // TODO: Configure for the used APIs & Endpoints

    String URL = "https://blooming-taiga-70606.herokuapp.com/backend/";

    @GET("travelInfo")
    Observable<List<TravelInfo>> getTravelInfo();

}
