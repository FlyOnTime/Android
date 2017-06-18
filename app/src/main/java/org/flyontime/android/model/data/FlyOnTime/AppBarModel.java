package org.flyontime.android.model.data.FlyOnTime;

/**
 * Created by jossi on 18.06.2017.
 */

public class AppBarModel {
    private String flightNo;
    private String description;
    private String date;

    public AppBarModel(String flightNo, String description, String date) {
        this.flightNo = flightNo;
        this.description = description;
        this.date = date;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
