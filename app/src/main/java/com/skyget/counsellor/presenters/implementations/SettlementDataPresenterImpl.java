package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.SettlementResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.ISettlementDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ISettlementMainView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettlementDataPresenterImpl implements ISettlementDataPresenter {

    ISettlementMainView iSettlementMainView;
    Context i;

    public SettlementDataPresenterImpl(ISettlementMainView iSettlementMainView, Context i) {
        this.iSettlementMainView = iSettlementMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getSalesmanSetlementDetails(int salesmanId) {
        iSettlementMainView.showProgress();
        Call<List<SettlementResponse>> call = apiInterface.getSalesmanSetlementDetails(salesmanId);
        call.enqueue(new Callback<List<SettlementResponse>>() {
            @Override
            public void onResponse(Call<List<SettlementResponse>> call, Response<List<SettlementResponse>> response) {
                iSettlementMainView.hideProgress();
                if (response.isSuccessful()) {
                    iSettlementMainView.getSalesmanSetlementDetails(response.body());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        iSettlementMainView.failure("" + jObjError.getString("errorMessage"));
                    } catch (Exception e) {
                        iSettlementMainView.failure("" + e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<SettlementResponse>> call, Throwable t) {

                iSettlementMainView.failure("Error " + t.getMessage().toString());
                iSettlementMainView.hideProgress();

            }
        });

    }


}
