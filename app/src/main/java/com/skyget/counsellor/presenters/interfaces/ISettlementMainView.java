package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.SettlementResponse;

import java.util.List;

public interface ISettlementMainView {

    void showProgress();

    void hideProgress();

    void getSalesmanSetlementDetails(List<SettlementResponse> resp);

    void failure(String msg);


}
