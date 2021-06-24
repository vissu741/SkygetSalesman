package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FeedBackAlertResponce implements Serializable
{
    @Expose
    public Object comment;
    @SerializedName("feedBackStates")
    @Expose
    public String feedBackStates;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    private final static long serialVersionUID = -3259974418583337365L;

}
