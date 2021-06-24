package com.skyget.counsellor.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubjectwiselistRequest implements Serializable
{

    @SerializedName("subjectId")
    @Expose
    public Integer subjectId;
    @SerializedName("subjectName")
    @Expose
    public String subjectName;
    @SerializedName("subjectIcon")
    @Expose
    public String subjectIcon;
    @SerializedName("subjectImage")
    @Expose
    public String subjectImage;
    private final static long serialVersionUID = 5306500215225449225L;

}
 
