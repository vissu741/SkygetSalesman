package com.skyget.counsellor.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateStudentDetailsRequest implements Serializable {

    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("city")
    @Expose
    public String city;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}