package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PhysicsChapterlistResponce  implements Serializable
{
    @SerializedName("chaptername")
    @Expose
    public String chaptername;
    @SerializedName("type")
    @Expose
    public Object type;
    @SerializedName("videosForUnitWise")
    @Expose
    public List<VideosForUnitWiseResponse> videosForUnitWise = null;
    @SerializedName("chapterId")
    @Expose
    public Integer chapterId;
    @SerializedName("subjectId")
    @Expose
    public Integer subjectId;
    @SerializedName("chapterInfo")
    @Expose
    public Object chapterInfo;
    private final static long serialVersionUID = 8271766567740283941L;


}







