package de.example.jw.mvi_app.model.service;

import java.util.List;

import de.example.jw.mvi_app.model.data.SchoolsModel;
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
