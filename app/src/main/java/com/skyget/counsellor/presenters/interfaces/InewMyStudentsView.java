package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.request.MynewStudentsListResponse;
import com.skyget.counsellor.model.response.FeedBackAlertResponce;
import com.skyget.counsellor.model.response.Update_city_Response;

import java.util.List;

public interface InewMyStudentsView {
    void showProgress();

    void sendFeedBackSuccess(FeedBackAlertResponce resp);


    void updateCityDetailSuccess(Update_city_Response resp);

    void sendFeedBackFailed(String msg);

    void hideProgress();


    void MynewStudents(List<MynewStudentsListResponse> resp);

    void failure(String msg);


}




