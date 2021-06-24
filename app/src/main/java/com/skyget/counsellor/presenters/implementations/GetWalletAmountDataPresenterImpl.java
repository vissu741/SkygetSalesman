package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.request.GetWalletAmountRequest;
import com.skyget.counsellor.model.response.GetWalletAmountResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetWalletAmountDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetWalletAmountMainView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetWalletAmountDataPresenterImpl implements IGetWalletAmountDataPresenter {

    IGetWalletAmountMainView iGetWalletAmountMainView;
    Context i;

    public GetWalletAmountDataPresenterImpl(IGetWalletAmountMainView iGetWalletAmountMainView, Context i) {
        this.iGetWalletAmountMainView = iGetWalletAmountMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getWalletAmount(GetWalletAmountRequest getWalletAmountRequest) {
        iGetWalletAmountMainView.showProgress();
        Call<GetWalletAmountResponse> call = apiInterface.getWalletAmount(getWalletAmountRequest);
        call.enqueue(new Callback<GetWalletAmountResponse>() {
            @Override
            public void onResponse(Call<GetWalletAmountResponse> call, Response<GetWalletAmountResponse> response) {
                iGetWalletAmountMainView.hideProgress();
                if (response.isSuccessful()) {
                    iGetWalletAmountMainView.getWalletAmount(response.body());

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iGetWalletAmountMainView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iGetWalletAmountMainView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<GetWalletAmountResponse> call, Throwable t) {

                iGetWalletAmountMainView.failure("Error " + t.getMessage().toString());
                iGetWalletAmountMainView.hideProgress();

            }
        });

    }


}
