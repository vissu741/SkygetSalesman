package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.SalesDetailsResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.ISalesmanSalesDataPresenter;
import com.skyget.counsellor.presenters.interfaces.ISalesmanSalesMainView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SalesmanSalesDataPresenterImpl implements ISalesmanSalesDataPresenter {

    ISalesmanSalesMainView iLoginMainView;
    Context i;

    public SalesmanSalesDataPresenterImpl(ISalesmanSalesMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getSalesmanSalesDetails(int salesman_id) {
        iLoginMainView.showProgress();
        Call<List<SalesDetailsResponse>> call = apiInterface.getSalesmanSalesDetails(salesman_id);
        call.enqueue(new Callback<List<SalesDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<SalesDetailsResponse>> call, Response<List<SalesDetailsResponse>> response) {
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
            public void onFailure(Call<List<SalesDetailsResponse>> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
