package com.skyget.counsellor.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FeedbackAlertRequest implements Serializable {
    @SerializedName("studentId")
    public long studentId;

  }
