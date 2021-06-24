package com.skyget.counsellor.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class newstudentsRequest  implements Serializable {


    @SerializedName("studentid")
    @Expose
    public String studentid;
    @SerializedName("name")
    @Expose
    public String name;

}
