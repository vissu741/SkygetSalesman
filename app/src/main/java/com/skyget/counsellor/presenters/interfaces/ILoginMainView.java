package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.LoginResponse;

public interface ILoginMainView {

    void showProgress();

    void hideProgress();

    void getResult(LoginResponse resp);

    void failure(String msg);


}
