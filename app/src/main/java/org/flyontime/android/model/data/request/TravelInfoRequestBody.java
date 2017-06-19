
package org.flyontime.android.model.data.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelInfoRequestBody {

    @SerializedName("airlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("originFlightDate")
    @Expose
    private String originFlightDate;
    @SerializedName("numberOfBags")
    @Expose
    private Integer numberOfBags;
    @SerializedName("numberOfSpecialBags")
    @Expose
    private Integer numberOfSpecialBags;

    public TravelInfoRequestBody(String airlineCode, String flightNumber, String originFlightDate, Integer numberOfBags, Integer numberOfSpecialBags) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.originFlightDate = originFlightDate;
        this.numberOfBags = numberOfBags;
        this.numberOfSpecialBags = numberOfSpecialBags;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOriginFlightDate() {
        return originFlightDate;
    }

    public void setOriginFlightDate(String originFlightDate) {
        this.originFlightDate = originFlightDate;
    }

    public Integer getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(Integer numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Integer getNumberOfSpecialBags() {
        return numberOfSpecialBags;
    }

    public void setNumberOfSpecialBags(Integer numberOfSpecialBags) {
        this.numberOfSpecialBags = numberOfSpecialBags;
    }

}
