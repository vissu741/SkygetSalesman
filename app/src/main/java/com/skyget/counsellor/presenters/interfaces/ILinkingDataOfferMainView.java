package com.skyget.counsellor.presenters.interfaces;


import com.skyget.counsellor.model.response.LinkingResponse;

import java.util.List;

public interface ILinkingDataOfferMainView {

    void showProgress();

    void hideProgress();

    void getLinkingViews(List<LinkingResponse> resp);

    void failure(String msg);


}
