package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClickingAlertResponce implements Serializable
{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("salesmanId")
    @Expose
    public Object salesmanId;
    @SerializedName("student_id")
    @Expose
    public Integer studentId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("category")
    @Expose
    public Object category;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("emailId")
    @Expose
    public Object emailId;
    @SerializedName("views")
    @Expose
    public Integer views;
    @SerializedName("comment")
    @Expose
    public Object comment;
    @SerializedName("feedBackStates")
    @Expose
    public String feedBackStates;
    private final static long serialVersionUID = 8118246463230752564L;

}

