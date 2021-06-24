package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.FeedBackAlertResponce;
import com.skyget.counsellor.model.response.Post;

import java.util.List;

public interface IFeedbackMainview {


    static void hideProgress(List<Post> body) {
    }

    void failure(String msg);

    void hideprogress();

    void FeedBackSuccessResponse(FeedBackAlertResponce resp);
    void FeedBackFaillure(String massage);



}
