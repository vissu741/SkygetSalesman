package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SalesmanReportsResponse implements Serializable {

    @SerializedName("noOfSubscriptions")
    @Expose
    public int noOfSubscriptions;
    @SerializedName("totalAmount")
    @Expose
    public double totalAmount;
    /*@SerializedName("paid")
    @Expose
    public double paid;
    @SerializedName("pending")
    @Expose
    public int pending;*/
    @SerializedName("salesinfo")
    @Expose
    public List<Salesinfo> salesinfo = null;

    public class Salesinfo implements Serializable {

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
        public double subscriptionAmount;
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
        @SerializedName("paidpercentage")
        @Expose
        public int paidpercentage;
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
        public double paidAmount;
        @SerializedName("paid")
        @Expose
        public boolean paid;
        @SerializedName("subscriptionName")
        @Expose
        public String subscriptionName;
        @SerializedName("salesManAmount")
        @Expose
        public double salesManAmount;
        @SerializedName("studentName")
        @Expose
        public String studentName;

    }

}
