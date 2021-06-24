package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.request.ContactedRequest;
import com.skyget.counsellor.model.request.CreateSalesOfferRequest;
import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IContactedDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IContactedMainView;
import com.skyget.counsellor.presenters.interfaces.ICreateSalesOfferDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ICreateSalesOfferMainView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactedDataPresenterImpl implements IContactedDataPresenter {

    IContactedMainView iContactedMainView;
    Context i;

    public ContactedDataPresenterImpl(IContactedMainView iContactedMainView, Context i) {
        this.iContactedMainView = iContactedMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void contacted(ContactedRequest contactedRequest) {
        iContactedMainView.showProgress();
        Call<DefaultSuccessResponse> call = apiInterface.contacted(contactedRequest);
        call.enqueue(new Callback<DefaultSuccessResponse>() {
            @Override
            public void onResponse(Call<DefaultSuccessResponse> call, Response<DefaultSuccessResponse> response) {
                iContactedMainView.hideProgress();
                if (response.isSuccessful()) {
                    iContactedMainView.getResultContacted(response.body());

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iContactedMainView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iContactedMainView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<DefaultSuccessResponse> call, Throwable t) {

                iContactedMainView.failure("Error " + t.getMessage().toString());
                iContactedMainView.hideProgress();

            }
        });

    }


}
