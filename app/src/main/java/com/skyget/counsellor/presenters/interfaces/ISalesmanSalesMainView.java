package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.SalesDetailsResponse;

import java.util.List;

public interface ISalesmanSalesMainView {

    void showProgress();

    void hideProgress();

    void getResult(List<SalesDetailsResponse> resp);

    void failure(String msg);


}
