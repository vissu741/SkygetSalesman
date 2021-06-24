package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.LinkingResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.ILinkingDataOfferMainView;
import com.skyget.counsellor.presenters.interfaces.ILinkingDataPresenter;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LinkingDataDataPresenterImpl implements ILinkingDataPresenter {

    ILinkingDataOfferMainView iLoginMainView;
    Context i;

    public LinkingDataDataPresenterImpl(ILinkingDataOfferMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getLinkingViews(int salesman_id, int mnth_id) {
        iLoginMainView.showProgress();

        Call<List<LinkingResponse>> call = apiInterface.getLinkingViews(salesman_id, mnth_id);
        call.enqueue(new Callback<List<LinkingResponse>>() {
            @Override
            public void onResponse(Call<List<LinkingResponse>> call, Response<List<LinkingResponse>> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {
                    iLoginMainView.getLinkingViews(response.body());

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iLoginMainView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iLoginMainView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<LinkingResponse>> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
