package com.skyget.counsellor.presenters.interfaces;

import com.skyget.counsellor.model.request.CreateSalesOfferRequest;
import com.skyget.counsellor.model.response.FeedBackAlertResponce;

public interface IFeedBackAlertDialogPresenter {
    void FeedBackAlort();

    void FeedBackAlort(FeedBackAlertResponce feedBackAlertResponce);
}
