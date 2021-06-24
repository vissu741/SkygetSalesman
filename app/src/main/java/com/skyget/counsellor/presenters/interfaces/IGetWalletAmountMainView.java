package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.GetWalletAmountResponse;

public interface IGetWalletAmountMainView {

    void showProgress();

    void hideProgress();

    void getWalletAmount(GetWalletAmountResponse resp);

    void failure(String msg);


}
