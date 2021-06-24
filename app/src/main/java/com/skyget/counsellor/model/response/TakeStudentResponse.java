package com.skyget.counsellor.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TakeStudentResponse implements Serializable {

    @SerializedName("subject")
    @Expose
    public String subject;
    @SerializedName("listitems")
    @Expose
    public List<Listitem> listitems = null;
    private final static long serialVersionUID = -2751689811614530220L;

    public class Listitem implements Serializable {

        @SerializedName("chaptername")
        @Expose
        public String chaptername;
        @SerializedName("type")
        @Expose
        public Object type;
        @SerializedName("videosForUnitWise")
        @Expose
        public List<VideosForUnitWise> videosForUnitWise = null;
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


        public class VideosForUnitWise implements Serializable {

            @SerializedName("topicId")
            @Expose
            public Integer topicId;
            @SerializedName("videoLink")
            @Expose
            public String videoLink;
            @SerializedName("tumbNail")
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

    }


}















