package org.flyontime.android.model.service;

import org.flyontime.android.model.data.SchoolsModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by JW on 24.04.2017.
 */

public interface SchoolsDataAPI {

    // TODO: Configure for the used APIs & Endpoints

    String URL = "https://raw.githubusercontent.com/Datenschule/schulscraper-data/master/schools/";

    @GET("hamburg.json")
    Observable<List<SchoolsModel>> getHamburgSchools();

}
