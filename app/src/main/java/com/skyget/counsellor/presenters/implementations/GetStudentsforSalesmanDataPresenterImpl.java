package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.GetStudentsforSalesmanResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetStudentsforSalesmanMainView;
import com.skyget.counsellor.presenters.interfaces.IGetStudentsforSalesmanDataPresenter;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetStudentsforSalesmanDataPresenterImpl implements IGetStudentsforSalesmanDataPresenter {

    IGetStudentsforSalesmanMainView iLoginMainView;
    Context i;

    public GetStudentsforSalesmanDataPresenterImpl(IGetStudentsforSalesmanMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getStudentsForSalesmanDetails(int salesman_id) {
        iLoginMainView.showProgress();
        Call<List<GetStudentsforSalesmanResponse>> call = apiInterface.getStudentsforSalesman(salesman_id);
        call.enqueue(new Callback<List<GetStudentsforSalesmanResponse>>() {
            @Override
            public void onResponse(Call<List<GetStudentsforSalesmanResponse>> call, Response<List<GetStudentsforSalesmanResponse>> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {
                        iLoginMainView.getResultStudentsforSalesman(response.body());

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
            public void onFailure(Call<List<GetStudentsforSalesmanResponse>> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
