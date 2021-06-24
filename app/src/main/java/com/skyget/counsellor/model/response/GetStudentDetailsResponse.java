package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetStudentDetailsResponse implements Serializable {

    @SerializedName("studentid")
    @Expose
    public int studentid;
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
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("sourcetype")
    @Expose
    public String sourcetype;
    @SerializedName("quota")
    @Expose
    public String quota;
    @SerializedName("standardName")
    @Expose
    public String standardName;
    @SerializedName("categoryName")
    @Expose
    public String categoryName;
    @SerializedName("studentImage")
    @Expose
    public String studentImage;
    @SerializedName("examTypes")
    @Expose
    public String examTypes;
    @SerializedName("categoryImage")
    @Expose
    public String categoryImage;
    @SerializedName("standardImage")
    @Expose
    public String standardImage;
    @SerializedName("percentage")
    @Expose
    public int percentage;
    @SerializedName("score")
    @Expose
    public int score;
    @SerializedName("totalMarks")
    @Expose
    public int totalMarks;
    @SerializedName("standardId")
    @Expose
    public int standardId;
    @SerializedName("categoryId")
    @Expose
    public int categoryId;
    @SerializedName("stateId")
    @Expose
    public int stateId;
    @SerializedName("cityid")
    @Expose
    public int cityid;
    @SerializedName("insertedDate")
    @Expose
    public String insertedDate;
    @SerializedName("updatedDate")
    @Expose
    public String updatedDate;
    @SerializedName("referralCode")
    @Expose
    public String referralCode;
    @SerializedName("accountVerified")
    @Expose
    public boolean accountVerified;


}
