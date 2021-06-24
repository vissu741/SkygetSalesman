package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.request.SubjectwiselistRequest;
import com.skyget.counsellor.model.response.Chapterwise_list_Response;

import java.util.List;

public interface I_IntractivePracticemainview {
    void showProgress();

    void hideProgress();

    void IntractivePracticeTab(List<SubjectwiselistRequest> subjectwiselistRequests);

    void failure(String msg);

    void getChaptersResponse(List<Chapterwise_list_Response> chapterwise_list_response);
}
