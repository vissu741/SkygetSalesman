package com.skyget.counsellor.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentIdforChapter {
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("subjectId")
    @Expose
    public String subjectId;

}
