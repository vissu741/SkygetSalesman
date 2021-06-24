package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.request.StudentFilterRequest;
import com.skyget.counsellor.model.request.TakeStudentRequest;

public interface INewstudentsPresenter {
    void newstudentsList(StudentFilterRequest studentFilterRequest);
    void TakeButtonApi(TakeStudentRequest takeStudentRequest);

}
