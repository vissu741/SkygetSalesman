package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideosForUnitWiseResponse implements Serializable
{

  //  @SerializedName("topicId")
    //@Expose
    //public Integer topicId;
    //@SerializedName("videoLink")
    //@Expose
    //public String videoLink;
    //@SerializedName("tumbNail")
    @Expose
    public String tumbNail;
    @SerializedName("topic")
    @Expose
    public String topic;
    @SerializedName("videoId")
    @Expose
    public Integer videoId;
    private final static long serialVersionUID = -7341126822442314165L;

}

