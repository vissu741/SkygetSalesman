package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Chapterwise_list_Response implements Serializable {


    @SerializedName("studentId")
    @Expose
    public Integer studentId;
    @SerializedName("subjectId")
    @Expose
    public Integer subjectId;
    @SerializedName("chapterId")
    @Expose
    public Integer chapterId;
    @SerializedName("examId")
    @Expose
    public Integer examId;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("stage1Status")
    @Expose
    public Integer stage1Status;
    @SerializedName("stage2Status")
    @Expose
    public Integer stage2Status;
    @SerializedName("stage3Status")
    @Expose
    public Integer stage3Status;
    @SerializedName("stage4Status")
    @Expose
    public Integer stage4Status;
    @SerializedName("stage3GoalTime")
    @Expose
    public Integer stage3GoalTime;
    @SerializedName("stage4GoalTime")
    @Expose
    public Integer stage4GoalTime;
    @SerializedName("stage3SpentTime")
    @Expose
    public Integer stage3SpentTime;
    @SerializedName("stage4SpentTime")
    @Expose
    public Integer stage4SpentTime;
    @SerializedName("Chapter")
    @Expose
    public String chapter;
    @SerializedName("chapterIcon")
    @Expose
    public String chapterIcon;
    @SerializedName("chapteColorCode")
    @Expose
    public String chapteColorCode;
    @SerializedName("roboName")
    @Expose
    public String roboName;
    @SerializedName("issubscribed")
    @Expose
    public Boolean issubscribed;
    @SerializedName("noOfTestsInLiveZone")
    @Expose
    public Integer noOfTestsInLiveZone;
    @SerializedName("cieachExamFocusStatus")
    @Expose
    public CieachExamFocusStatus cieachExamFocusStatus;
    private final static long serialVersionUID = 5398208992844901344L;



}

class CieachExamFocusStatus implements Serializable {

    @SerializedName("firstFocusExamtype")
    @Expose
    public String firstFocusExamtype;
    @SerializedName("firstFocusExamtypeId")
    @Expose
    public Integer firstFocusExamtypeId;
    @SerializedName("firstFocusExamIcon")
    @Expose
    public String firstFocusExamIcon;
    @SerializedName("roboName")
    @Expose
    public Object roboName;
    @SerializedName("considerCat")
    @Expose
    public Boolean considerCat;
    @SerializedName("firstFocustimeSpent")
    @Expose
    public Integer firstFocustimeSpent;
    @SerializedName("firstFocustargetGoalTime")
    @Expose
    public Integer firstFocustargetGoalTime;
    @SerializedName("firstFocusProgress")
    @Expose
    public Integer firstFocusProgress;
    @SerializedName("parllelfocusexamtypes")
    @Expose
    public List<Object> parllelfocusexamtypes = null;
    private final static long serialVersionUID = 704376923702725667L;

}
