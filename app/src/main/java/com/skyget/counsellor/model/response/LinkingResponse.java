package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class LinkingResponse implements Serializable {

    @SerializedName("supervisor")
    @Expose
    public int supervisor;
    @SerializedName("salesmanId")
    @Expose
    public int salesmanId;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("amount")
    @Expose
    public float amount;
    @SerializedName("superVisorAmount")
    @Expose
    public float superVisorAmount;
    @SerializedName("paid")
    @Expose
    public boolean paid;
}
