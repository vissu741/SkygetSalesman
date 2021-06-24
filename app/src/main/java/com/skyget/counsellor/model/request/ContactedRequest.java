package com.skyget.counsellor.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContactedRequest implements Serializable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("comment")
    @Expose
    public String comment;
}
