package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllSubscriptionsResponse implements Serializable {

    @SerializedName("studentId")
    @Expose
    public int studentId;

    @SerializedName("subscriptionId")
    @Expose
    public int subscriptionId;
    @SerializedName("BuyId")
    @Expose
    public int buyId;
    @SerializedName("academicyearId")
    @Expose
    public int academicyearId;
    @SerializedName("orginalprice")
    @Expose
    public int orginalprice;
    @SerializedName("offerprice")
    @Expose
    public double offerprice;
    @SerializedName("academicyear")
    @Expose
    public String academicyear;
    @SerializedName("examtypeId")
    @Expose
    public List<Integer> examtypeId = null;
    @SerializedName("categoryId")
    @Expose
    public int categoryId;
    @SerializedName("StandardId")
    @Expose
    public int standardId;
    @SerializedName("activeDate")
    @Expose
    public String activeDate;
    @SerializedName("expiryDate")
    @Expose
    public String expiryDate;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("Standard")
    @Expose
    public String standard;
    @SerializedName("examtype")
    @Expose
    public String examtype;

    @SerializedName("discount")
    @Expose
    public int discount;
    @SerializedName("tax")
    @Expose
    public int tax;
    @SerializedName("totalAfterDiscount")
    @Expose
    public int totalAfterDiscount;
    @SerializedName("grandtotal")
    @Expose
    public int grandtotal;

    @SerializedName("walletAmountUsed")
    @Expose
    public int walletAmountUsed;

    @SerializedName("Type")
    @Expose
    public int type;
    @SerializedName("imageUrl")
    @Expose
    public Object imageUrl;


}
