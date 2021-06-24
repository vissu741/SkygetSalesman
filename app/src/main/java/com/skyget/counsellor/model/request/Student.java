package com.skyget.counsellor.model.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Student implements Serializable {

    @SerializedName("student_id")
    @Expose
    public int studentId;
    @SerializedName("selfassign")
    @Expose
    public boolean selfassign;

}
