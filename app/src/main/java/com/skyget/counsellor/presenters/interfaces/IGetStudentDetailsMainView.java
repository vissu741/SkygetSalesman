package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.GetStudentDetailsResponse;

public interface IGetStudentDetailsMainView {

    void showProgress();

    void hideProgress();

    void getStudentDetails(GetStudentDetailsResponse resp);

    void failure(String msg);


}
