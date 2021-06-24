package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("salesMan")
    @Expose
    public String salesMan;
    @SerializedName("address1")
    @Expose
    public String address1;
    @SerializedName("address2")
    @Expose
    public String address2;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("group")
    @Expose
    public String group;
   /* @SerializedName("registeredDate")
    @Expose
    public int registeredDate;*/
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("insertedBy")
    @Expose
    public String insertedBy;
    @SerializedName("updatedBy")
    @Expose
    public String updatedBy;
    @SerializedName("updatedDate")
    @Expose
    public String updatedDate;
    @SerializedName("percentage")
    @Expose
    public int percentage;
    @SerializedName("value")
    @Expose
    public int value;
    @SerializedName("details")
    @Expose
    public String details;
    @SerializedName("supervisor")
    @Expose
    public int supervisor;

    @SerializedName("stateId")
    @Expose
    public int stateId;

    @SerializedName("cityId")
    @Expose
    public int cityId;

    @SerializedName("emi")
    @Expose
    public boolean emi;


}
