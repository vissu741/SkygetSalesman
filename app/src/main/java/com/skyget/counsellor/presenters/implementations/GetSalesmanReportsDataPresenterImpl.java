package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.SalesmanReportsResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetSalesmanReportsDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetSalesmanReportsMainView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetSalesmanReportsDataPresenterImpl implements IGetSalesmanReportsDataPresenter {

    IGetSalesmanReportsMainView iLoginMainView;
    Context i;

    public GetSalesmanReportsDataPresenterImpl(IGetSalesmanReportsMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getSalesmanReports(int salesman_id, int mnth_id) {
        iLoginMainView.showProgress();
        Call<SalesmanReportsResponse> call = apiInterface.getSalesmanReports(salesman_id,mnth_id);
        call.enqueue(new Callback<SalesmanReportsResponse>() {
            @Override
            public void onResponse(Call<SalesmanReportsResponse> call, Response<SalesmanReportsResponse> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {
                        iLoginMainView.getResult(response.body());

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
            public void onFailure(Call<SalesmanReportsResponse> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
