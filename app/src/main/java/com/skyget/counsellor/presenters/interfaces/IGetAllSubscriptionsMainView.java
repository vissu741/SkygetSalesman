package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.AllSubscriptionsResponse;

import java.util.List;

public interface IGetAllSubscriptionsMainView {

    void showProgress();

    void hideProgress();

    void getResult(List<AllSubscriptionsResponse> resp);

    void failure(String msg);


}
