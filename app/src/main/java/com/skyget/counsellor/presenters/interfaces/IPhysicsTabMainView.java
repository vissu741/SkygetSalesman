package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.response.PhysicsChapterlistResponce;
import com.skyget.counsellor.model.response.TakeStudentResponse;

import java.util.List;

import androidx.fragment.app.Fragment;

public interface IPhysicsTabMainView {
     void showProgress();

    void hideProgress();

    void  PhysicsTab (List<TakeStudentResponse> chapterlistResponce);

    void failure(String msg);


}
