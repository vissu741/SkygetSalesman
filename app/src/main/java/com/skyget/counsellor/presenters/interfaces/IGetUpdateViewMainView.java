package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.DefaultSuccessResponse;

public interface IGetUpdateViewMainView {

    void showProgress();

    void hideProgress();

    void getResult(DefaultSuccessResponse resp);

    void failure(String msg);


}
