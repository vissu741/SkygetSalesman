package com.skyget.counsellor.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateSalesOfferRequest implements Serializable {

    @SerializedName("studentId")
    public long studentId;
    @SerializedName("planId")
    public long planId;
    @SerializedName("subscriptionAmount")
    public double subscriptionAmount;
    @SerializedName("percentage")
    public int percentage;
    @SerializedName("offerType")
    public String offerType;
    @SerializedName("offerAmount")
    public double offerAmount;
    @SerializedName("salesPersonId")
    public long salesPersonId;
    @SerializedName("validityInhrs")
    public int validityInhrs;


    ///////////////////////////////////

    @SerializedName("gstAmount")
    public double gstAmount;
    @SerializedName("walletAmount")
    public double walletAmount;
    @SerializedName("emiMonths")
    public int emiMonths;
    @SerializedName("downPayment")
    public double downPayment;
    @SerializedName("emiAmount")
    public double emiAmount;
    @SerializedName("totalAmountToBePaid")
    public double totalAmountToBePaid;
    @SerializedName("everyMonthDueDate")
    public String everyMonthDueDate;

}
