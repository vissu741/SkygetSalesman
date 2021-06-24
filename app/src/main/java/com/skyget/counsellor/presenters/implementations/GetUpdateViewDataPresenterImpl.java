package com.skyget.counsellor.presenters.implementations;

import android.content.Context;

import com.skyget.counsellor.model.response.DefaultSuccessResponse;
import com.skyget.counsellor.other.network.APIClient;
import com.skyget.counsellor.other.network.ApiInterface;
import com.skyget.counsellor.presenters.interfaces.IGetUpdateViewDataPresenter;
import com.skyget.counsellor.presenters.interfaces.IGetUpdateViewMainView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetUpdateViewDataPresenterImpl implements IGetUpdateViewDataPresenter {

    IGetUpdateViewMainView iLoginMainView;
    Context i;

    public GetUpdateViewDataPresenterImpl(IGetUpdateViewMainView iLoginMainView, Context i) {
        this.iLoginMainView = iLoginMainView;
        this.i = i;
    }

    ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

    @Override
    public void getUpdateView(int salesman_id, int std_id) {
        iLoginMainView.showProgress();
        Call<DefaultSuccessResponse> call = apiInterface.getUpdateView(salesman_id, std_id);
        call.enqueue(new Callback<DefaultSuccessResponse>() {
            @Override
            public void onResponse(Call<DefaultSuccessResponse> call, Response<DefaultSuccessResponse> response) {
                iLoginMainView.hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().status) {
                        iLoginMainView.getResult(response.body());
                    }

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
            public void onFailure(Call<DefaultSuccessResponse> call, Throwable t) {

                iLoginMainView.failure("Error " + t.getMessage().toString());
                iLoginMainView.hideProgress();

            }
        });

    }


}
