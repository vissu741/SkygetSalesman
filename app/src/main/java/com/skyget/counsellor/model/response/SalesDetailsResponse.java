package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SalesDetailsResponse implements Serializable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("studentId")
    @Expose
    public int studentId;
    @SerializedName("planId")
    @Expose
    public int planId;
    @SerializedName("subscriptionAmount")
    @Expose
    public int subscriptionAmount;
    @SerializedName("percentage")
    @Expose
    public int percentage;
    @SerializedName("offerAmount")
    @Expose
    public int offerAmount;
    @SerializedName("insertedDate")
    @Expose
    public String insertedDate;
    @SerializedName("salesPersonId")
    @Expose
    public int salesPersonId;
    @SerializedName("offerActivetedDate")
    @Expose
    public String offerActivetedDate;
    @SerializedName("offerExpiredDate")
    @Expose
    public String offerExpiredDate;
    @SerializedName("validityInhrs")
    @Expose
    public int validityInhrs;
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("mobileNumber")
    @Expose
    public String mobileNumber;
    @SerializedName("offerType")
    @Expose
    public String offerType;
    @SerializedName("walletamount")
    @Expose
    public int walletamount;
    @SerializedName("withdrawlAmount2x")
    @Expose
    public int withdrawlAmount2x;
    @SerializedName("paidAmount")
    @Expose
    public int paidAmount;
    @SerializedName("paid")
    @Expose
    public boolean paid;
    @SerializedName("paidpercentage")
    @Expose
    public int paidpercentage;
    @SerializedName("studentName")
    @Expose
    public String studentName;
    @SerializedName("salesManAmount")
    @Expose
    public int salesManAmount;
    @SerializedName("subscriptionName")
    @Expose
    public String subscriptionName;


}
