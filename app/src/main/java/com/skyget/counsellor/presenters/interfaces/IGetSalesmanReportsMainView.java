package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.SalesmanReportsResponse;

public interface IGetSalesmanReportsMainView {

    void showProgress();

    void hideProgress();

    void getResult(SalesmanReportsResponse resp);

    void failure(String msg);


}
