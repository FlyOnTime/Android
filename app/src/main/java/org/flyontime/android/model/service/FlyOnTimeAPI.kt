package org.flyontime.android.model.service

import io.reactivex.Observable
import org.flyontime.android.model.data.FlyOnTime.TravelInfoResponse
import org.flyontime.android.model.data.request.TravelInfoRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by JW on 24.04.2017.
 */

interface FlyOnTimeAPI {

    @Deprecated("Deprecated in API")
    @GET("travelInfo")
    fun travelInfo(): Observable<TravelInfoResponse>

    @POST("travelInfo")
    fun getTravelInfoPost(@Body requestBody: TravelInfoRequestBody): Observable<TravelInfoResponse>

    companion object {

        // TODO: Configure for the used APIs & Endpoints

        val URL = "https://blooming-taiga-70606.herokuapp.com/backend/"
    }

}
