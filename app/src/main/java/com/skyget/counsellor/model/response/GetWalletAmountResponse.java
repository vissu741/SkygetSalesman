package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetWalletAmountResponse implements Serializable {

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
    public double walletAmount;
    @SerializedName("refferalAmount")
    @Expose
    public double refferalAmount;
    @SerializedName("salesPersonGaveOffer")
    @Expose
    public double salesPersonGaveOffer;
    @SerializedName("taxAmount")
    @Expose
    public double taxAmount;
    @SerializedName("taxPercentage")
    @Expose
    public int taxPercentage;
    @SerializedName("imageUrl")
    @Expose
    public String imageUrl;
    @SerializedName("subscribed")
    @Expose
    public boolean subscribed;
    @SerializedName("salePaersonOfferid")
    @Expose
    public int salePaersonOfferid;
    @SerializedName("downPayment")
    @Expose
    public double downPayment;
    @SerializedName("emi")
    @Expose
    public double emi;
}
