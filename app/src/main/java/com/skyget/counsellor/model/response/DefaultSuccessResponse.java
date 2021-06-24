package com.skyget.counsellor.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class DefaultSuccessResponse implements Serializable {

    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public boolean status;
}
