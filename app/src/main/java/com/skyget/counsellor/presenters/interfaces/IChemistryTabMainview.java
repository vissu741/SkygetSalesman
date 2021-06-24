package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.response.PhysicsChapterlistResponce;
import com.skyget.counsellor.model.response.TakeStudentResponse;

import java.util.List;

public interface IChemistryTabMainview {
    void showProgress();

    void hideProgress();

    void ChemistryTab(List<TakeStudentResponse> chapterlistResponce);

    void failure(String msg);

}
