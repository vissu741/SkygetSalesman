package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubscriptionsResponse implements Serializable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("subsciptionPeriod")
    @Expose
    public String subsciptionPeriod;
    @SerializedName("amount")
    @Expose
    public double amount;
    @SerializedName("walletAmount")
    @Expose
    public int walletAmount;
    @SerializedName("refferalAmount")
    @Expose
    public int refferalAmount;
    @SerializedName("salesPersonGaveOffer")
    @Expose
    public int salesPersonGaveOffer;


}
