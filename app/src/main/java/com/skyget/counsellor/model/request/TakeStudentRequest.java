package com.skyget.counsellor.model.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TakeStudentRequest implements Serializable {

    @SerializedName("students")
    @Expose
    public List<Student> students = null;
    @SerializedName("salesManId")
    @Expose
    public String salesManId;

}
