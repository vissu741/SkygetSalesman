package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {
    @SerializedName("studentid")
    @Expose
    public Integer studentid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("emailid")
    @Expose
    public String emailid;
    @SerializedName("mobilenumber")
    @Expose
    public String mobilenumber;
    @SerializedName("city")
    @Expose
    public Object city;
    @SerializedName("state")
    @Expose
    public Object state;
    @SerializedName("gender")
    @Expose
    public Object gender;
    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("sourcetype")
    @Expose
    public Object sourcetype;
    @SerializedName("quota")
    @Expose
    public Object quota;
    @SerializedName("standardName")
    @Expose
    public Object standardName;
    @SerializedName("categoryName")
    @Expose
    public String categoryName;
    @SerializedName("studentImage")
    @Expose
    public Object studentImage;
    @SerializedName("examTypes")
    @Expose
    public Object examTypes;
    @SerializedName("categoryImage")
    @Expose
    public Object categoryImage;
    @SerializedName("standardImage")
    @Expose
    public Object standardImage;
    @SerializedName("percentage")
    @Expose
    public Integer percentage;
    @SerializedName("score")
    @Expose
    public Integer score;
    @SerializedName("totalMarks")
    @Expose
    public Integer totalMarks;
    @SerializedName("standardId")
    @Expose
    public Integer standardId;
    @SerializedName("categoryId")
    @Expose
    public Integer categoryId;
    @SerializedName("stateId")
    @Expose
    public Integer stateId;
    @SerializedName("cityid")
    @Expose
    public Integer cityid;
    @SerializedName("insertedDate")
    @Expose
    public Object insertedDate;
    @SerializedName("updatedDate")
    @Expose
    public Object updatedDate;
    @SerializedName("referralCode")
    @Expose
    public Object referralCode;
    @SerializedName("accountVerified")
    @Expose
    public Boolean accountVerified;

}
