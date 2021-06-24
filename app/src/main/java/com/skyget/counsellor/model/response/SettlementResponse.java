package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SettlementResponse implements Serializable {


    @SerializedName("salesmanId")
    @Expose
    public int salesmanId;
    @SerializedName("salesmanName")
    @Expose
    public String salesmanName;
    @SerializedName("peroid")
    @Expose
    public String peroid;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("insertedDate")
    @Expose
    public long insertedDate;


}
