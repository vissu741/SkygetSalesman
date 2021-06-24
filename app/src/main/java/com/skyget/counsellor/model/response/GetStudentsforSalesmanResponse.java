package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetStudentsforSalesmanResponse implements Serializable {

    @SerializedName("salesmanId")
    @Expose
    public int salesmanId;
    @SerializedName("student_id")
    @Expose
    public int studentId;
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
    public String category;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("emailId")
    @Expose
    public String emailId;
    @SerializedName("views")
    @Expose
    public int views;

    @SerializedName("comment")
    @Expose
    public String comment;

    @SerializedName("id")
    @Expose
    public Integer id;



}
