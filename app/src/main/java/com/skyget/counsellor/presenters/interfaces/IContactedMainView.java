package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.DefaultSuccessResponse;

public interface IContactedMainView {

    void showProgress();

    void hideProgress();

    void getResultContacted(DefaultSuccessResponse resp);

    void failure(String msg);


}
