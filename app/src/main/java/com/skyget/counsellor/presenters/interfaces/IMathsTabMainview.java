package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.response.TakeStudentResponse;

import java.util.List;

public interface IMathsTabMainview {

    void showProgress();

    void hideProgress();

    void MathsTab(List<TakeStudentResponse> chapterlistResponce);

    void failure(String msg);
}
