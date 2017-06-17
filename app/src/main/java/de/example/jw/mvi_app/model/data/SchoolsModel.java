
package de.example.jw.mvi_app.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SchoolsModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("school_type")
    @Expose
    private String schoolType;
    @SerializedName("legal_status")
    @Expose
    private Integer legalStatus;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("programs")
    @Expose
    private Programs programs;
    @SerializedName("full_time_school")
    @Expose
    private Boolean fullTimeSchool;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    public SchoolsModel(String name, String id, String address, String schoolType, Integer legalStatus, String fax, String phone, String website, String email, String provider, String state, Programs programs, Boolean fullTimeSchool, Double lon, Double lat) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.schoolType = schoolType;
        this.legalStatus = legalStatus;
        this.fax = fax;
        this.phone = phone;
        this.website = website;
        this.email = email;
        this.provider = provider;
        this.state = state;
        this.programs = programs;
        this.fullTimeSchool = fullTimeSchool;
        this.lon = lon;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public Integer getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(Integer legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public Boolean getFullTimeSchool() {
        return fullTimeSchool;
    }

    public void setFullTimeSchool(Boolean fullTimeSchool) {
        this.fullTimeSchool = fullTimeSchool;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(id).append(address).append(schoolType).append(legalStatus).append(fax).append(phone).append(website).append(email).append(provider).append(state).append(programs).append(fullTimeSchool).append(lon).append(lat).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SchoolsModel) == false) {
            return false;
        }
        SchoolsModel rhs = ((SchoolsModel) other);
        return new EqualsBuilder().append(name, rhs.name).append(id, rhs.id).append(address, rhs.address).append(schoolType, rhs.schoolType).append(legalStatus, rhs.legalStatus).append(fax, rhs.fax).append(phone, rhs.phone).append(website, rhs.website).append(email, rhs.email).append(provider, rhs.provider).append(state, rhs.state).append(programs, rhs.programs).append(fullTimeSchool, rhs.fullTimeSchool).append(lon, rhs.lon).append(lat, rhs.lat).isEquals();
    }

}
