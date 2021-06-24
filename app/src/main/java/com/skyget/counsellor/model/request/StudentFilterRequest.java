package com.skyget.counsellor.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentFilterRequest implements Serializable {
    @SerializedName("stateId")
    @Expose
    public Integer stateId;
    @SerializedName("cityId")
    @Expose
    public Integer cityId;
    @SerializedName("fromdate")
    @Expose
    public String fromdate;
    @SerializedName("todate")
    @Expose
    public String todate;

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public String getFromdate() {
        return fromdate;
    }

    public String getTodate() {
        return todate;
    }


}
