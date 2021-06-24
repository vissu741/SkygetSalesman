package com.skyget.counsellor.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetWalletAmountRequest implements Serializable {

    @SerializedName("studentId")
    public long studentId;

}
