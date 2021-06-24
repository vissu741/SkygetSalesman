package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.model.response.Post;

import java.util.List;

public interface InewStudentsView {


    void showProgress();

    void hideProgress();

    void NewStudentsList(List<Post> resp);

    void failure(String msg);

    void takeButtonSuccessResponse(DefaultSuccessResponse resp);

    void takeButtonFailure(String erroMessage);





}
