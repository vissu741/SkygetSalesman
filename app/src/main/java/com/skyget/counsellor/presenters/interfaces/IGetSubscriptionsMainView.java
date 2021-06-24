package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.SubscriptionsResponse;

import java.util.List;

public interface IGetSubscriptionsMainView {

    void showProgress();

    void hideProgress();

    void getResult(List<SubscriptionsResponse> resp);

    void failure(String msg);


}
